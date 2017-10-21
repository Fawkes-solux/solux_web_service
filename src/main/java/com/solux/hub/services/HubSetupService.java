package com.solux.hub.services;

import com.solux.hub.dto.HubSetupDTO;
import com.solux.hub.dto.ResponseDTO;
import com.solux.hub.enums.ErrorCode;
import com.solux.hub.enums.ErrorFlag;

public class HubSetupService {

    private static int validate(HubSetupDTO setupDTO) {
        if (setupDTO == null) {
           return ErrorCode.nullData;
        }

        if (setupDTO.getId() == null || setupDTO.getId().isEmpty()) {
            return ErrorCode.nullId;
        }

        if (setupDTO.getPanelId() == null || setupDTO.getPanelId().isEmpty()) {
            return ErrorCode.nullPanelId;
        }

        return 0;
    }

    public static ResponseDTO buildResponse(HubSetupDTO setupDTO) {

        int status;

        if ((status = validate(setupDTO)) > 0) {
            return new ResponseDTO(ErrorFlag.failure, status);
        }

        return new ResponseDTO(ErrorFlag.success,status);

    }
}
