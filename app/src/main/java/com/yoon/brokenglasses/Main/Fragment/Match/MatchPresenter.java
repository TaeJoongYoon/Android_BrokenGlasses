package com.yoon.brokenglasses.Main.Fragment.Match;

/**
 * Created by Yoon on 2017-12-27.
 */

public class MatchPresenter implements MatchContract.Presenter {
    private MatchContract.View view;

    public MatchPresenter(MatchContract.View view){
        this.view = view;
    }
}
