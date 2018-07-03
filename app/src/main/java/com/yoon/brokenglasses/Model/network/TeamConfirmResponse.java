package com.yoon.brokenglasses.Model.network;

/**
 * Created by Yoon on 2018-02-02.
 */

public class TeamConfirmResponse {
    private boolean error;

    public TeamConfirmResponse(boolean error){
        this.error = error;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
