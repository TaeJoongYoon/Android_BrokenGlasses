package com.yoon.brokenglasses.Model.network;

/**
 * Created by Yoon on 2017-12-27.
 */

public class EmailResponse {
    private String email;
    private boolean error;
    private String errorMsg;

    public EmailResponse(String email, boolean error, String errorMsg) {
        this.email = email;
        this.error = error;
        this.errorMsg = errorMsg;
    }

    public String getEmail() {
        return email;
    }

    public boolean isError() {
        return error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
