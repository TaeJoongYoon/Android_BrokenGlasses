package com.yoon.brokenglasses.Model.network;

/**
 * Created by Yoon on 2017-12-27.
 */

public class SignInPost {
    private String email;
    private String password;

    public SignInPost(String email, String password){
        this.email = email;
        this.password = password;
    }
}
