package com.yoon.brokenglasses.Search;

import com.yoon.brokenglasses.Model.Region;
import com.yoon.brokenglasses.Model.SearchResult;
import com.yoon.brokenglasses.Model.User;
import com.yoon.brokenglasses.Model.network.TeamNameCheckPost;
import com.yoon.brokenglasses.Model.network.TeamNameCheckResponse;
import com.yoon.brokenglasses.Model.network.TeamPositionPost;
import com.yoon.brokenglasses.Model.network.TeamPositionResponse;
import com.yoon.brokenglasses.Model.network.TeamSearchPost;
import com.yoon.brokenglasses.Model.network.TeamSearchResponse;
import com.yoon.brokenglasses.Network.NetworkUtil;
import com.yoon.brokenglasses.UserSingleton;
import com.yoon.brokenglasses.databinding.ActivitySearchBinding;
import com.yoon.brokenglasses.databinding.FragmentTeamBinding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by Yoon on 2018-02-02.
 */

public class SearchPresenter implements SearchContract.Presenter {
    private SearchContract.View view;
    private UserSingleton userSingleton = UserSingleton.getInstance();

    private String teamName;
    private String teamBirth;
    private String captainUID;
    private List<Region> region = new ArrayList<>();
    private List<String> formation = new ArrayList<>();

    public SearchPresenter(SearchContract.View view){
        this.view = view;
    }

    @Override
    public void register_name_chk(String teamName) {
        NetworkUtil.brokenGlassesServiceRequest()
                .requestTeamName(new TeamNameCheckPost(teamName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<TeamNameCheckResponse>() {
                    @Override
                    public void onNext(TeamNameCheckResponse teamNameCheckResponse) {
                        if(teamNameCheckResponse.isError())
                            view.name_chk_failed();
                        else
                            view.name_chk_success();
                    }

                    @Override
                    public void onError(Throwable t) {
                        view.name_chk_failed();
                        NetworkUtil.networkErrorToast(t,User.class);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void register(ActivitySearchBinding binding) {
        chkInfo(binding);
        chkRegion(binding);
        chkFormation(binding);
        requestTeam();
    }

    private void chkInfo(ActivitySearchBinding binding){
        teamName = binding.teamRegisterName.getText().toString();
        teamBirth = binding.teamRegisterBirth.getSelectedItem().toString();
        captainUID = userSingleton.getUserUid();
    }

    private void chkRegion(ActivitySearchBinding binding){
        region.clear();
        if(binding.teamRegisterMain1.getSelectedItemPosition() != 0 &&
                binding.teamRegisterSub1.isSelected()) {
            Region region = new Region(binding.teamRegisterMain1.getSelectedItem().toString(), binding.teamRegisterSub1.getSelectedItem().toString());
            this.region.add(region);
        }
        if(binding.teamRegisterMain2.getSelectedItemPosition() != 0 &&
                binding.teamRegisterSub2.isSelected()) {
            Region region = new Region(binding.teamRegisterMain2.getSelectedItem().toString(), binding.teamRegisterSub2.getSelectedItem().toString());
            this.region.add(region);
        }
        if(binding.teamRegisterMain3.getSelectedItemPosition() != 0 &&
                binding.teamRegisterSub3.isSelected()) {
            Region region = new Region(binding.teamRegisterMain3.getSelectedItem().toString(), binding.teamRegisterSub3.getSelectedItem().toString());
            this.region.add(region);
        }
    }

    private void chkFormation(ActivitySearchBinding binding){
        formation.clear();
        if(binding.teamRegisterFm1.getSelectedItemPosition() != 0 &&
                binding.teamRegisterFm1.isSelected())
            formation.add(binding.teamRegisterFm1.getSelectedItem().toString());
        if(binding.teamRegisterFm2.getSelectedItemPosition() != 0 &&
                binding.teamRegisterFm2.isSelected())
            formation.add(binding.teamRegisterFm2.getSelectedItem().toString());
        if(binding.teamRegisterFm3.getSelectedItemPosition() != 0 &&
                binding.teamRegisterFm3.isSelected())
            formation.add(binding.teamRegisterFm3.getSelectedItem().toString());
    }

    private void requestTeam(){
        NetworkUtil.brokenGlassesServiceRequest()
                .requestTeamPosition(new TeamPositionPost(teamName,teamBirth,captainUID,region,formation))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<TeamPositionResponse>() {
                    @Override
                    public void onNext(TeamPositionResponse teamPositionResponse) {
                        if(teamPositionResponse.isError())
                            view.register_failed();
                        else{
                            userSingleton.setTeamUid(teamPositionResponse.getTeamUID());
                            view.register_success();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        view.register_failed();
                        NetworkUtil.networkErrorToast(t,User.class);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void search(String teamName) {
        NetworkUtil.brokenGlassesServiceRequest()
                .requestTeamSearch(new TeamSearchPost(teamName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<List<TeamSearchResponse>>() {
                    @Override
                    public void onNext(List<TeamSearchResponse> responses) {
                        view.search_success(responses);
                    }

                    @Override
                    public void onError(Throwable t) {
                        view.search_failed();
                        NetworkUtil.networkErrorToast(t,User.class);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
