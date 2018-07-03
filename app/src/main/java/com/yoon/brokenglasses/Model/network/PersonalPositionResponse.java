package com.yoon.brokenglasses.Model.network;

/**
 * Created by Yoon on 2018-01-15.
 */

public class PersonalPositionResponse {
    private boolean error;
    private String errorMsg;

    public PersonalPositionResponse(boolean error, String errorMsg){
        this.error = error;
        this.errorMsg = errorMsg;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
