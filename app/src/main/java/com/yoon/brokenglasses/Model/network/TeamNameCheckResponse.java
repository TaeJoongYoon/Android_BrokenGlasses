package com.yoon.brokenglasses.Model.network;

/**
 * Created by Yoon on 2018-01-30.
 */

public class TeamNameCheckResponse {
    private boolean error;

    public TeamNameCheckResponse(boolean error){
        this.error = error;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
