package com.solux.hub;

import com.solux.hub.dto.DealerBoardDTO;
import com.solux.hub.dto.HubPingDTO;
import com.solux.hub.dto.HubSetupDTO;
import com.solux.hub.dto.ResponseDTO;
import com.solux.hub.services.HubPingService;
import com.solux.hub.services.HubSetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class SoluxHubController {

    @Autowired
    private HubSetupService setupService;

    @Autowired
    private HubPingService pingService;

    @RequestMapping(value = "/initiate/setup", method = RequestMethod.POST)
    public ResponseDTO initiateSetup(@RequestBody HubSetupDTO setupDTO) {
        Logger logger = Logger.getLogger(SoluxHubController.class.getSimpleName());
        logger.log(Level.INFO, " Received Request ");

        return setupService.buildResponse(setupDTO);

    }

    @RequestMapping(value = "/ping/service", method = RequestMethod.POST)
    public ResponseDTO pingService(@RequestBody HubPingDTO pingDTO) {
        Logger logger = Logger.getLogger(SoluxHubController.class.getSimpleName());
        logger.log(Level.INFO, " Received Request ");

        return pingService.buildResponse(pingDTO);
    }

    @RequestMapping(value = "/solux/dealerBoard", method = RequestMethod.GET)
    public DealerBoardDTO getDealerDetails(@RequestParam(value = "dealerId", defaultValue = "0") String dealerId) {
        Logger logger = Logger.getLogger(SoluxHubController.class.getSimpleName());
        logger.log(Level.INFO, " Received Request ");

        return getDealerObjectDTO();
    }

    private DealerBoardDTO getDealerObjectDTO() {
        DealerBoardDTO dealerBoardDTO = new DealerBoardDTO();
        dealerBoardDTO.setErrorFlag(true);
        dealerBoardDTO.setErroMsg("qwerty");

        DealerBoardDTO.Data dealerData = new DealerBoardDTO.Data();
        dealerData.setDealerId("123");
        dealerData.setDealerName("AASASA");
        dealerData.setTotalCapacity(12.12);
        dealerData.setTotalEffeciency(13.12);

        DealerBoardDTO.MetricsData metricsData = new DealerBoardDTO.MetricsData();
        metricsData.setTimestamp(new Date());
        metricsData.setType("volt");
        metricsData.setValue(323.23);
        List<DealerBoardDTO.MetricsData> metricsDataList = new ArrayList<>();
        metricsDataList.add(metricsData);

        DealerBoardDTO.Metrics metrics = new DealerBoardDTO.Metrics();
        metrics.setDataItems(metricsDataList);

        DealerBoardDTO.Customer customer = new DealerBoardDTO.Customer();
        customer.setCustomerId(1);
        customer.setCustomerName("qwqwqw");
        customer.setCapacity(12.12);
        customer.setLat(23.23f);
        customer.setLng(31.32f);
        customer.setLocation("323w wewe  wew");


        dealerData.setMetrics(metrics);
        dealerData.setCustomer(customer);


        dealerBoardDTO.setData(dealerData);

        return dealerBoardDTO;
    }
}
