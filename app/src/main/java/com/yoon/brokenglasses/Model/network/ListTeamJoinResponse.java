package com.yoon.brokenglasses.Model.network;

import com.yoon.brokenglasses.Model.User;

/**
 * Created by Yoon on 2018-02-02.
 */

public class ListTeamJoinResponse {
    private User user;
    private String date;

    public ListTeamJoinResponse(User user, String date) {
        this.user = user;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
