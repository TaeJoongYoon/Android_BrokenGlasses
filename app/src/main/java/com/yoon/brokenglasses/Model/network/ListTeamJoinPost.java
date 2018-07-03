package com.yoon.brokenglasses.Model.network;

/**
 * Created by Yoon on 2018-02-02.
 */

public class ListTeamJoinPost {
    private String teamUID;

    public ListTeamJoinPost(String teamUID){
        this.teamUID = teamUID;
    }

    public String getTeamUID() {
        return teamUID;
    }

    public void setTeamUID(String teamUID) {
        this.teamUID = teamUID;
    }
}
