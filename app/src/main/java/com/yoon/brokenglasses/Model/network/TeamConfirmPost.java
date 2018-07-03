package com.yoon.brokenglasses.Model.network;

/**
 * Created by Yoon on 2018-02-02.
 */

public class TeamConfirmPost {
    private String teamUID;
    private String userUID;
    private boolean confirm;

    public TeamConfirmPost(String teamUID, String userUID, boolean confirm) {
        this.teamUID = teamUID;
        this.userUID = userUID;
        this.confirm = confirm;
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

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }
}
