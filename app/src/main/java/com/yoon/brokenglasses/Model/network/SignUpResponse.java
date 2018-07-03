package com.yoon.brokenglasses.Model.network;

/**
 * Created by Yoon on 2017-12-27.
 */

public class SignUpResponse {
    private String userUID;
    private String email;
    private boolean error;
    private String errorMsg;

    public SignUpResponse(String userUID, String email, boolean error, String errorMsg){
        this.userUID = userUID;
        this.email = email;
        this.error = error;
        this.errorMsg = errorMsg;
    }

    public String getUserUID() {
        return userUID;
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
