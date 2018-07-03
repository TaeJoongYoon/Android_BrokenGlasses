package com.yoon.brokenglasses.Main.Fragment.Player;

import com.yoon.brokenglasses.Model.Region;
import com.yoon.brokenglasses.Model.User;
import com.yoon.brokenglasses.Model.network.PersonalPositionPost;
import com.yoon.brokenglasses.Model.network.PersonalPositionResponse;
import com.yoon.brokenglasses.Model.network.UserPost;
import com.yoon.brokenglasses.Model.network.UserResponse;
import com.yoon.brokenglasses.Network.NetworkUtil;
import com.yoon.brokenglasses.UserSingleton;
import com.yoon.brokenglasses.databinding.FragmentPlayerBinding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by Yoon on 2017-12-27.
 */

public class PlayerPresenter implements PlayerContract.Presenter {
    private PlayerContract.View view;

    private UserSingleton userSingleton = UserSingleton.getInstance();
    private String birth;
    private int height;
    private int weight;
    private String job;

    private List<String> positions = new ArrayList<>();
    private List<Region> regions = new ArrayList<>();

    public PlayerPresenter(PlayerContract.View view){
        this.view = view;
    }


    @Override
    public void userRequest(String userUID) {
        NetworkUtil.brokenGlassesServiceRequest()
                .requestUser(new UserPost(userUID))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<UserResponse>() {
                    @Override
                    public void onNext(UserResponse userResponse) {
                        if(userResponse.getJob() == null)
                            view.userFailed();
                        else
                            view.userSuccess();
                    }

                    @Override
                    public void onError(Throwable t) {
                        NetworkUtil.networkErrorToast(t,User.class);
                        view.userFailed();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void register(FragmentPlayerBinding binding) {
        infoCheck(binding);
        regionCheck(binding);
        positionCheck(binding);
        requestPersonal();
    }

    private void infoCheck(FragmentPlayerBinding binding){
        birth = binding.personalEtBirthday.getText().toString();
        height = Integer.parseInt(binding.personalEtHeight.getText().toString());
        weight = Integer.parseInt(binding.personalEtWeight.getText().toString());
        job = binding.personalJobSpinner.getSelectedItem().toString();
    }

    private void regionCheck(FragmentPlayerBinding binding){
        regions.clear();
        if(binding.personalMain1.getSelectedItemPosition() != 0 &&
                binding.personalSub1.isSelected()) {
            Region region = new Region(binding.personalMain1.getSelectedItem().toString(), binding.personalSub1.getSelectedItem().toString());
            regions.add(region);
        }
        if(binding.personalMain2.getSelectedItemPosition() != 0 &&
                binding.personalSub2.isSelected()) {
            Region region = new Region(binding.personalMain2.getSelectedItem().toString(), binding.personalSub2.getSelectedItem().toString());
            regions.add(region);
        }
        if(binding.personalMain3.getSelectedItemPosition() != 0 &&
                binding.personalSub3.isSelected()) {
            Region region = new Region(binding.personalMain3.getSelectedItem().toString(), binding.personalSub3.getSelectedItem().toString());
            regions.add(region);
        }
    }

    private void positionCheck(FragmentPlayerBinding binding){
        positions.clear();
        if(binding.personalLFW.isChecked())
            positions.add("LFW");
        if(binding.personalCFW.isChecked())
            positions.add("CFW");
        if(binding.personalRFW.isChecked())
            positions.add("RFW");
        if(binding.personalACM.isChecked())
            positions.add("ACM");
        if(binding.personalLWM.isChecked())
            positions.add("LWM");
        if(binding.personalCM.isChecked())
            positions.add("CM");
        if(binding.personalRWM.isChecked())
            positions.add("RWM");
        if(binding.personalDCM.isChecked())
            positions.add("DCM");
        if(binding.personalLB.isChecked())
            positions.add("LB");
        if(binding.personalCD.isChecked())
            positions.add("CD");
        if(binding.personalRB.isChecked())
            positions.add("RB");
        if(binding.personalGK.isChecked())
            positions.add("GK");
    }

    private void requestPersonal(){
        NetworkUtil.brokenGlassesServiceRequest()
                .requestPersonalPosition(new PersonalPositionPost(userSingleton.getUserUid(),birth,height,weight,job,positions,regions))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<PersonalPositionResponse>() {
                    @Override
                    public void onNext(PersonalPositionResponse personalPositionResponse) {
                        if(personalPositionResponse.isError())
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
