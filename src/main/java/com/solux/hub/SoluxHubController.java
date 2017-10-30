package com.solux.hub;

import com.solux.hub.dto.HubPingDTO;
import com.solux.hub.dto.HubSetupDTO;
import com.solux.hub.dto.ResponseDTO;
import com.solux.hub.services.HubPingService;
import com.solux.hub.services.HubSetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
