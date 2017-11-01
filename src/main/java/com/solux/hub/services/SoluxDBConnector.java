package com.solux.hub.services;

import com.solux.hub.dto.DealerBoardDTO;
import com.solux.hub.dto.HubPingDTO;
import com.solux.hub.dto.HubSetupDTO;
import com.solux.hub.model.entity.SoluxCustomer;
import com.solux.hub.model.entity.SoluxDealer;
import com.solux.hub.model.entity.SoluxHub;
import com.solux.hub.model.entity.SoluxPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class SoluxDBConnector {

    private final EntityManagerFactory emfactory;
    private final EntityManager entitymanager;
    private Logger logger = Logger.getLogger(SoluxDBConnector.class.getSimpleName());

    public SoluxDBConnector() {

        emfactory = Persistence.createEntityManagerFactory("JPA");
        entitymanager = emfactory.createEntityManager();

    }

    public void insertHubData(HubSetupDTO setupDTO) {

        try {

            EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JPA");
            EntityManager entitymanager = emfactory.createEntityManager();
            entitymanager.getTransaction().begin();

            SoluxHub soluxHub = new SoluxHub();
            soluxHub.setHub_id(setupDTO.getId());
            soluxHub.setLat(setupDTO.getLat());
            soluxHub.setLng(setupDTO.getLng());

            entitymanager.merge(soluxHub);

            for (String panelId : setupDTO.getPanelId()) {

                SoluxPanel soluxPanel = new SoluxPanel();
                soluxPanel.setPanel_id(panelId);
                soluxPanel.setSoluxHub(soluxHub);
                soluxPanel.setTimestampType(new Date());

                entitymanager.merge(soluxPanel);
            }


            entitymanager.getTransaction().commit();
            entitymanager.close();
            emfactory.close();

        } catch (Exception e) {
            logger.log(Level.INFO, e.getMessage());
        }
    }

    public void insertPingInfo(HubPingDTO pingDTO) {

        try {
            EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JPA");
            EntityManager entitymanager = emfactory.createEntityManager();
            entitymanager.getTransaction().begin();

            SoluxHub soluxHub = new SoluxHub();
            soluxHub.setHub_id(pingDTO.getId());

            for (HubPingDTO.Panel panel : pingDTO.getData().getPanelList()) {

                SoluxPanel panelInfo = new SoluxPanel();
                panelInfo.setPanel_id(panel.getPanel_id());
                panelInfo.setCurr(panel.getCurr());
                panelInfo.setVolt(panel.getVolt());
                panelInfo.setPower(panel.getCurr() * panel.getVolt());
                panelInfo.setTimestampType(new Date());

                entitymanager.merge(soluxHub);

                panelInfo.setSoluxHub(soluxHub);

                entitymanager.merge(panelInfo);
            }

            entitymanager.getTransaction().commit();
            entitymanager.close();
            emfactory.close();

        } catch (Exception e) {
            logger.log(Level.INFO, e.getMessage());
        }

    }

    public DealerBoardDTO fetchDealerInfo(String dealerId) {

        DealerBoardDTO dealerBoardDTO = new DealerBoardDTO();
        DealerBoardDTO.Data dealerData = new DealerBoardDTO.Data();
        DealerBoardDTO.MetricsData metricsData = new DealerBoardDTO.MetricsData();
        DealerBoardDTO.Metrics metrics = new DealerBoardDTO.Metrics();
        DealerBoardDTO.Customer customer = new DealerBoardDTO.Customer();

        dealerBoardDTO.setErrorFlag(true);
        dealerBoardDTO.setErroMsg("qwerty");

        try {

            entitymanager.getTransaction().begin();

            //SoluxDealer soluxDealer = entitymanager.find(SoluxDealer.class, dealerId);

            dealerData.setDealerId(dealerId);
            dealerData.setDealerName("AASASA");
            dealerData.setTotalCapacity(12.12);
            dealerData.setTotalEffeciency(13.12);

            dealerBoardDTO.setData(dealerData);

            List<SoluxCustomer> customerList = entitymanager.
                    createQuery("SELECT customer FROM SoluxCustomer as customer WHERE customer.soluxDealer = '" +
                            dealerId + "'").getResultList();

            for (SoluxCustomer soluxCustomer : customerList) {

                logger.log(Level.INFO, "" + soluxCustomer.getCust_id());
                logger.log(Level.INFO, soluxCustomer.getCust_name());
                logger.log(Level.INFO, "" + soluxCustomer.getLocation());

                customer.setCustomerId(soluxCustomer.getCust_id());
                customer.setCustomerName(soluxCustomer.getCust_name());
                customer.setCapacity(soluxCustomer.getCapacity());
                customer.setLat(soluxCustomer.getSoluxHub().getLat());
                customer.setLng(soluxCustomer.getSoluxHub().getLng());
                customer.setLocation(soluxCustomer.getLocation());

                List<SoluxPanel> panelList = entitymanager.
                        createQuery("SELECT panel FROM SoluxPanel as panel WHERE panel.soluxHub ='"+
                                soluxCustomer.getSoluxHub().getHub_id() + "'").getResultList();

                List<DealerBoardDTO.MetricsData> metricsDataList = new ArrayList<>();

                for (SoluxPanel panel : panelList) {
                    metricsData.setTimestamp(panel.getTimestampType());
                    metricsData.setValue(panel.getVolt());
                    metricsData.setType("volt");
                    metricsDataList.add(metricsData);

                }

                metrics.setDataItems(metricsDataList);
            }

            dealerData.setMetrics(metrics);
            dealerData.setCustomer(customer);

            entitymanager.close();
            emfactory.close();

        } catch (Exception ex) {
            logger.log(Level.INFO, ex.getMessage());
        }

        return dealerBoardDTO;
    }

    public void insertDealerInfo(DealerBoardDTO dealerBoardDTO) {

        try {

            entitymanager.getTransaction().begin();

            SoluxHub soluxHub = new SoluxHub();
            soluxHub.setHub_id(dealerBoardDTO.getData().getDealerId());
            entitymanager.merge(soluxHub);

            SoluxDealer soluxDealer = new SoluxDealer();
            soluxDealer.setDealerId(dealerBoardDTO.getData().getDealerId());
            soluxDealer.setDealerName(dealerBoardDTO.getData().getDealerName());
            entitymanager.merge(soluxDealer);

            SoluxCustomer soluxCustomer = new SoluxCustomer();
            soluxCustomer.setCust_id(dealerBoardDTO.getData().getCustomer().getCustomerId());
            soluxCustomer.setCust_name(dealerBoardDTO.getData().getCustomer().getCustomerName());
            soluxCustomer.setCapacity(dealerBoardDTO.getData().getCustomer().getCapacity());
            soluxCustomer.setSoluxDealer(soluxDealer);
            soluxCustomer.setSoluxHub(soluxHub);

            entitymanager.merge(soluxCustomer);

            entitymanager.getTransaction().commit();
            entitymanager.close();
            emfactory.close();


        } catch (Exception ex) {
            logger.log(Level.INFO, ex.getMessage());
        }
    }

}
