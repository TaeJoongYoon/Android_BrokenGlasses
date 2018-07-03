package com.yoon.brokenglasses.Main.Fragment.Home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoon.brokenglasses.R;

public class HomeFragment extends Fragment implements HomeContract.View {

    private HomePresenter presenter;

    public HomeFragment() {
        presenter = new HomePresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public HomeContract.Presenter getPresenter() {
        return presenter;
    }
}
