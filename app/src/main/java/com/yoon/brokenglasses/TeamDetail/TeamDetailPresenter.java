package com.yoon.brokenglasses.TeamDetail;

import com.yoon.brokenglasses.Model.User;
import com.yoon.brokenglasses.Model.network.TeamCheckPost;
import com.yoon.brokenglasses.Model.network.TeamCheckResponse;
import com.yoon.brokenglasses.Model.network.TeamJoinPost;
import com.yoon.brokenglasses.Model.network.TeamJoinResponse;
import com.yoon.brokenglasses.Model.network.TeamPost;
import com.yoon.brokenglasses.Model.network.TeamResponse;
import com.yoon.brokenglasses.Network.NetworkUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by Yoon on 2018-02-02.
 */

public class TeamDetailPresenter implements TeamDetailContract.Presenter {
    private TeamDetailContract.View view;

    public TeamDetailPresenter(TeamDetailContract.View view){
        this.view = view;
    }

    @Override
    public void teamDetail(String teamUID, String userUID) {
        NetworkUtil.brokenGlassesServiceRequest()
                .requestTeamCheck(new TeamCheckPost(teamUID,userUID))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<TeamCheckResponse>() {
                    @Override
                    public void onNext(TeamCheckResponse teamResponse) {
                        view.setView(teamResponse);
                    }

                    @Override
                    public void onError(Throwable t) {
                        NetworkUtil.networkErrorToast(t,User.class);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void teamJoin(String teamUID, String userUID) {
        NetworkUtil.brokenGlassesServiceRequest()
                .requestJoin(new TeamJoinPost(teamUID,userUID))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<TeamJoinResponse>() {
                    @Override
                    public void onNext(TeamJoinResponse teamResponse) {
                        view.view_finish();
                    }

                    @Override
                    public void onError(Throwable t) {
                        NetworkUtil.networkErrorToast(t,User.class);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
