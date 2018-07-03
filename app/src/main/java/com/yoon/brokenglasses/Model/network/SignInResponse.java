package com.yoon.brokenglasses.Model.network;

/**
 * Created by Yoon on 2017-12-27.
 */

public class SignInResponse {
    private String userUID;
    private String teamUID;
    private String email;
    private String userName;
    private boolean error;
    private String errorMsg;

    public SignInResponse(String userUID, String teamUID, String email, String userName, boolean error, String errorMsg){
        this.userUID = userUID;
        this.teamUID = teamUID;
        this.email = email;
        this.userName = userName;
        this.error = error;
        this.errorMsg = errorMsg;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public String getTeamUID() {
        return teamUID;
    }

    public void setTeamUID(String teamUID) {
        this.teamUID = teamUID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
