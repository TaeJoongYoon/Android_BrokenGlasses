package com.yoon.brokenglasses.Model.network;

/**
 * Created by Yoon on 2018-01-24.
 */

public class UserPost {
    private String userUID;

    public UserPost(String UID){
        this.userUID = UID;
    }

    public String getUID() {
        return userUID;
    }

    public void setUID(String UID) {
        this.userUID = UID;
    }
}
