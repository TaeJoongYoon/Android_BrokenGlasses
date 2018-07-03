package com.yoon.brokenglasses.Alarm;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.yoon.brokenglasses.Custom.SimpleDividerItemDecoration;
import com.yoon.brokenglasses.Model.User;
import com.yoon.brokenglasses.Model.network.ListTeamJoinResponse;
import com.yoon.brokenglasses.R;
import com.yoon.brokenglasses.Search.SearchRecyclerViewAdapter;
import com.yoon.brokenglasses.UserSingleton;
import com.yoon.brokenglasses.Util;
import com.yoon.brokenglasses.databinding.ActivityAlarmBinding;

import java.util.List;

public class AlarmActivity extends AppCompatActivity implements AlarmContract.View{
    private ActivityAlarmBinding binding;
    private AlarmPresenter presenter;
    private UserSingleton userSingleton = UserSingleton.getInstance();

    private TeamJoinRecyclerViewAdapter adapter;
    private List<ListTeamJoinResponse> responses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_alarm);
        binding.setActivity(this);
        presenter = new AlarmPresenter(this);

        init();
        setRecyclerView();
    }

    public void init(){
        presenter.request(userSingleton.getTeamUid());
    }

    public void setRecyclerView(){
        binding.listTeamJoinRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.listTeamJoinRecyclerview.addItemDecoration(new SimpleDividerItemDecoration(this));
        adapter = new TeamJoinRecyclerViewAdapter(this,presenter);
        binding.listTeamJoinRecyclerview.setAdapter(adapter);
    }

    @Override
    public void success(List<ListTeamJoinResponse> response) {
        responses = response;
        adapter.addItems(responses);
    }

    @Override
    public void failed() {
        Util.makeToast("데이터를 가져오는데 실패했습니다!");
    }

    @Override
    public void confirm(ListTeamJoinResponse response) {

        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + responses.size());
        presenter.confirm(userSingleton.getTeamUid(),response.getUser().getUserUID(), true);
        responses.remove(response);
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" + responses.size());
        adapter.addItems(responses);
        Util.makeToast("승인하셨습니다!");
    }

    @Override
    public void not_confirm(ListTeamJoinResponse response) {
        presenter.confirm(userSingleton.getTeamUid(),response.getUser().getUserUID(), false);
        responses.remove(response);
        adapter.addItems(responses);
        Util.makeToast("거절하셨습니다!");
    }
}
