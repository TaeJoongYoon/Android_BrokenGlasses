package com.yoon.brokenglasses.Model.network;

/**
 * Created by Yoon on 2018-01-30.
 */

public class TeamNameCheckPost {
    private String teamName;

    public TeamNameCheckPost(String teamName){
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
