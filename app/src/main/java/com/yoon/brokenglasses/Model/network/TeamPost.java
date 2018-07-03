package com.yoon.brokenglasses.Model.network;

/**
 * Created by Yoon on 2018-01-30.
 */

public class TeamPost {
    private String teamUID;

    public TeamPost(String teamUID){
        this.teamUID = teamUID;
    }

    public String getTeamUID() {
        return teamUID;
    }

    public void setTeamUID(String teamUID) {
        teamUID = teamUID;
    }
}
