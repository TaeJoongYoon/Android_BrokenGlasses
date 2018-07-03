package com.yoon.brokenglasses.Model.network;

/**
 * Created by Yoon on 2018-01-30.
 */

public class TeamPositionResponse {
    private boolean error;
    private String teamUID;

    public TeamPositionResponse(boolean error,String teamUID) {
        this.error = error;
        this.teamUID = teamUID;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getTeamUID() {
        return teamUID;
    }

    public void setTeamUID(String teamUID) {
        this.teamUID = teamUID;
    }
}
