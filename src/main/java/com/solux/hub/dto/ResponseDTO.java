package com.solux.hub.dto;

public class ResponseDTO {

    private int errFlag;
    private int errCode;

    public ResponseDTO(int errFlag, int errCode) {
        this.errFlag = errFlag;
        this.errCode = errCode;
    }

    public int getErrFlag() {
        return errFlag;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrFlag(int errFlag) {
        this.errFlag = errFlag;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }
}
