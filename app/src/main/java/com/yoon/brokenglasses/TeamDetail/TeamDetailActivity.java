package com.yoon.brokenglasses.TeamDetail;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yoon.brokenglasses.Model.network.TeamCheckResponse;
import com.yoon.brokenglasses.Model.network.TeamResponse;
import com.yoon.brokenglasses.R;
import com.yoon.brokenglasses.UserSingleton;
import com.yoon.brokenglasses.Util;
import com.yoon.brokenglasses.databinding.ActivityTeamDetailBinding;

public class TeamDetailActivity extends AppCompatActivity implements TeamDetailContract.View {

    private ActivityTeamDetailBinding binding;
    private TeamDetailPresenter presenter;

    private UserSingleton userSingleton = UserSingleton.getInstance();

    private String UID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_team_detail);
        binding.setActivity(this);
        presenter = new TeamDetailPresenter(this);

        init();
    }

    public void init(){
        Intent intent = getIntent();
        UID = intent.getStringExtra("UID");

        presenter.teamDetail(UID,userSingleton.getUserUid());
    }

    @Override
    public void setView(TeamCheckResponse teamResponse) {
        if(teamResponse.isRequest())
           Util.makeToast("이미 가입신청한 팀입니다!");

        if(userSingleton.getTeamUid() == null)
            binding.teamJoin.setVisibility(View.VISIBLE);

        binding.teamJoin.setOnClickListener(view -> {
            if(teamResponse.isRequest()){
                Util.makeToast("이미 가입신청한 팀입니다!");
            }
            else
                presenter.teamJoin(UID, userSingleton.getUserUid());
        });
    }

    @Override
    public void view_finish(){
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
