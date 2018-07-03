package com.yoon.brokenglasses.Main.Fragment.Home;

/**
 * Created by Yoon on 2017-12-27.
 */

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View view;

    public HomePresenter(HomeContract.View view){
        this.view = view;
    }
}
