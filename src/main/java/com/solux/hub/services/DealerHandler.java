package com.solux.hub.services;

import com.solux.hub.dto.DealerBoardDTO;
import com.solux.hub.enums.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DealerHandler {


    @Autowired
    private SoluxDBConnector dbConnector;

    private int validate(String dealerId) {
        if (dealerId == null) {
            return ErrorCode.nullData;
        }

        return ErrorCode.noError;
    }

    public DealerBoardDTO buildResponse(String dealerId) {

        int status;

        if ((status = validate(dealerId)) > 0) {
            return dbConnector.fetchDealerInfo(dealerId);

        }

        return dbConnector.fetchDealerInfo(dealerId);
    }
}
