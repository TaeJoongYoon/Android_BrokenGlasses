package com.yoon.brokenglasses.Model.network;

/**
 * Created by Yoon on 2017-12-27.
 */

public class SignUpPost {
    private String email;
    private String password;
    private String userName;

    public SignUpPost(String email, String password, String userName){
        this.email = email;
        this.password = password;
        this.userName = userName;
    }
}
