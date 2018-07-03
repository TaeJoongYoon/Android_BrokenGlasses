package com.yoon.brokenglasses.Splash;

/**
 * Created by Yoon on 2017-12-27.
 */

public class SplashContract {
    interface View{
        void toSignin();
        void toMain();
    }

    interface Presenter{
        void check_signed(String email, String password);
    }
}
