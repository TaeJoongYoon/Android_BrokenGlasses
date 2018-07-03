package com.yoon.brokenglasses.SignIn;

import com.yoon.brokenglasses.Model.User;
import com.yoon.brokenglasses.Model.network.SignInPost;
import com.yoon.brokenglasses.Model.network.SignInResponse;
import com.yoon.brokenglasses.Model.network.SignUpResponse;
import com.yoon.brokenglasses.Network.NetworkUtil;
import com.yoon.brokenglasses.UserSingleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by Yoon on 2017-12-27.
 */

public class SignInPresenter implements SignInContract.Presenter {

    private SignInContract.View view;
    private UserSingleton userSingleton = UserSingleton.getInstance();

    public SignInPresenter(SignInContract.View view){
        this.view = view;
    }

    @Override
    public void call_sign_in(String email, String password) {
        NetworkUtil.brokenGlassesServiceRequest()
                .requestSignIn(new SignInPost(email,password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<SignInResponse>() {
                    @Override
                    public void onNext(SignInResponse signInResponse) {
                        if (signInResponse.isError())
                            view.failed();
                        else {
                            userSingleton.setUserUid(signInResponse.getUserUID());
                            userSingleton.setTeamUid(signInResponse.getTeamUID());
                            userSingleton.setUserName(signInResponse.getUserName());
                            view.success(email, password);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        NetworkUtil.networkErrorToast(t, User.class);
                        view.failed();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
