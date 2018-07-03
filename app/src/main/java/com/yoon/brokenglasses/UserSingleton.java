package com.yoon.brokenglasses;

/**
 * Created by Yoon on 2018-01-19.
 */

public class UserSingleton {
    private static UserSingleton userSingleton;
    private String UserUid;
    private String userName;
    private String TeamUid;

    public static UserSingleton getInstance(){
        if(userSingleton == null)
            userSingleton = new UserSingleton();
        return userSingleton;
    }

    public String getUserUid() {
        return UserUid;
    }

    public void setUserUid(String userUid) {
        UserUid = userUid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTeamUid() {
        return TeamUid;
    }

    public void setTeamUid(String teamUid) {
        TeamUid = teamUid;
    }
}