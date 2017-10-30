package com.solux.hub.services;

import com.solux.hub.dto.HubPingDTO;
import com.solux.hub.dto.ResponseDTO;
import com.solux.hub.enums.ErrorCode;
import com.solux.hub.enums.ErrorFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HubPingService {

    @Autowired
    private SoluxDBConnector dbConnector;

    private int validate(HubPingDTO hubPingDTO) {
        if (hubPingDTO == null) {
            return ErrorCode.nullData;
        }

        if (hubPingDTO.getId() == null || hubPingDTO.getId().isEmpty()) {
            return ErrorCode.nullId;
        }

        if (hubPingDTO.getData() == null) {
            return ErrorCode.nullData;
        }


        return ErrorCode.noError;
    }

    public ResponseDTO buildResponse(HubPingDTO pingDTO) {

        int status;

        if ((status = validate(pingDTO)) > 0) {
            return new ResponseDTO(ErrorFlag.failure, status);
        }

        dbConnector.insertPingInfo(pingDTO);

        return new ResponseDTO(ErrorFlag.success,status);

    }

}
