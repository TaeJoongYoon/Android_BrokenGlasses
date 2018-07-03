package com.yoon.brokenglasses.Model.network;

/**
 * Created by Yoon on 2018-02-02.
 */

public class TeamJoinPost {
    private String teamUID;
    private String userUID;

    public TeamJoinPost(String teamUID, String userUID) {
        this.teamUID = teamUID;
        this.userUID = userUID;
    }

    public String getTeamUID() {
        return teamUID;
    }

    public void setTeamUID(String teamUID) {
        this.teamUID = teamUID;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }
}
