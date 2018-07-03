package com.yoon.brokenglasses.Main.Fragment.Team;

import com.yoon.brokenglasses.Model.Region;
import com.yoon.brokenglasses.Model.User;
import com.yoon.brokenglasses.Model.network.PersonalPositionPost;
import com.yoon.brokenglasses.Model.network.PersonalPositionResponse;
import com.yoon.brokenglasses.Model.network.TeamNameCheckPost;
import com.yoon.brokenglasses.Model.network.TeamNameCheckResponse;
import com.yoon.brokenglasses.Model.network.TeamPositionPost;
import com.yoon.brokenglasses.Model.network.TeamPositionResponse;
import com.yoon.brokenglasses.Model.network.TeamPost;
import com.yoon.brokenglasses.Model.network.TeamResponse;
import com.yoon.brokenglasses.Network.NetworkUtil;
import com.yoon.brokenglasses.UserSingleton;
import com.yoon.brokenglasses.Util;
import com.yoon.brokenglasses.databinding.FragmentTeamBinding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by Yoon on 2017-12-27.
 */

public class TeamPresenter implements TeamContract.Presenter {
    private TeamContract.View view;

    public TeamPresenter(TeamContract.View view){
        this.view = view;
    }

    @Override
    public void request(String teamUID) {
        NetworkUtil.brokenGlassesServiceRequest()
                .requestTeam(new TeamPost(teamUID))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<TeamResponse>() {
                    @Override
                    public void onNext(TeamResponse teamResponse) {
                        if(teamResponse.isError())
                            view.failed();
                        else
                            view.success();
                    }

                    @Override
                    public void onError(Throwable t) {
                        view.failed();
                        NetworkUtil.networkErrorToast(t,User.class);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
