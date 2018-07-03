package com.yoon.brokenglasses.SignIn;

import android.app.Activity;

/**
 * Created by Yoon on 2017-12-27.
 */

public class SignInContract {
    interface View{
        void success(String email, String password);
        void failed();
    }

    interface Presenter{
        void call_sign_in(String email, String password);
    }
}
