package com.yoon.brokenglasses.Main.Fragment.Match;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoon.brokenglasses.Main.Fragment.Home.HomeContract;
import com.yoon.brokenglasses.R;

public class MatchFragment extends Fragment implements MatchContract.View {

    private MatchPresenter presenter;

    public MatchFragment() {
        presenter = new MatchPresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_match, container, false);
    }

    @Override
    public MatchContract.Presenter getPresenter() {
        return presenter;
    }
}
