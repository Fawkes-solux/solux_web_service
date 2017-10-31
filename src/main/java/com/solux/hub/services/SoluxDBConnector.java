package com.solux.hub.services;

import com.solux.hub.dto.HubPingDTO;
import com.solux.hub.dto.HubSetupDTO;
import com.solux.hub.model.entity.SoluxHub;
import com.solux.hub.model.entity.SoluxPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class SoluxDBConnector {

    private Logger logger = Logger.getLogger(SoluxDBConnector.class.getSimpleName());

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

}
