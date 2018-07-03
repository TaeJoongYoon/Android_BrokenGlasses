package com.yoon.brokenglasses.Alarm;

import com.yoon.brokenglasses.Model.User;
import com.yoon.brokenglasses.Model.network.ListTeamJoinPost;
import com.yoon.brokenglasses.Model.network.ListTeamJoinResponse;
import com.yoon.brokenglasses.Model.network.TeamConfirmPost;
import com.yoon.brokenglasses.Model.network.TeamConfirmResponse;
import com.yoon.brokenglasses.Model.network.TeamSearchPost;
import com.yoon.brokenglasses.Model.network.TeamSearchResponse;
import com.yoon.brokenglasses.Network.NetworkUtil;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by Yoon on 2018-02-02.
 */

public class AlarmPresenter implements AlarmContract.Presenter {
    private static AlarmContract.View view;

    public AlarmPresenter(AlarmContract.View view){
        this.view = view;
    }

    @Override
    public void request(String teamUID) {
        NetworkUtil.brokenGlassesServiceRequest()
                .requestListTeamJoin(new ListTeamJoinPost(teamUID))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<List<ListTeamJoinResponse>>() {
                    @Override
                    public void onNext(List<ListTeamJoinResponse> responses) {
                        view.success(responses);
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

    @Override
    public void confirm(String teamUID, String userUID, boolean confirm) {
        NetworkUtil.brokenGlassesServiceRequest()
                .requestTeamConfirm(new TeamConfirmPost(teamUID,userUID,confirm))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<TeamConfirmResponse>() {
                    @Override
                    public void onNext(TeamConfirmResponse teamConfirmResponse) {

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


    public void onClickConfirm(ListTeamJoinResponse response){
        view.confirm(response);
    }

    public void onClickNotConfirm(ListTeamJoinResponse response){
        view.not_confirm(response);
    }
}
