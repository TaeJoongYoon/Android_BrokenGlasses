package com.yoon.brokenglasses.Model.network;

/**
 * Created by Yoon on 2018-02-02.
 */

public class TeamJoinResponse {
    private boolean error;

    public TeamJoinResponse(boolean error){
        this.error = error;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
