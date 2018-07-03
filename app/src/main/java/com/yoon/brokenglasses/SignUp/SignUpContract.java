package com.yoon.brokenglasses.SignUp;

import android.app.Activity;

/**
 * Created by Yoon on 2017-12-27.
 */

public class SignUpContract {
    interface View{
        void email_confirmed();
        void email_not_confirmed();
        void password_confirmed();
        void password_not_confirmed();
        void success();
        void failed();
    }

    interface Presenter{
        void check_email(String email);
        void check_password(String password, String password_2);
        void call_sign_up(String email, String password, String userName);
    }
}
