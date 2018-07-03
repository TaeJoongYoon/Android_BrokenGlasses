package com.yoon.brokenglasses.Splash;

import com.yoon.brokenglasses.Model.User;
import com.yoon.brokenglasses.Model.network.SignInPost;
import com.yoon.brokenglasses.Model.network.SignInResponse;
import com.yoon.brokenglasses.Network.NetworkUtil;
import com.yoon.brokenglasses.UserSingleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by Yoon on 2017-12-27.
 */

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View view;
    private UserSingleton userSingleton = UserSingleton.getInstance();

    public SplashPresenter(SplashContract.View view){
        this.view = view;
    }

    @Override
    public void check_signed(String email, String password) {
        if(email != null && password != null){
            NetworkUtil.brokenGlassesServiceRequest()
                    .requestSignIn(new SignInPost(email,password))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new ResourceSubscriber<SignInResponse>() {
                        @Override
                        public void onNext(SignInResponse signInResponse) {
                            if(signInResponse.isError())
                                view.toSignin();
                            else {
                                userSingleton.setUserUid(signInResponse.getUserUID());
                                userSingleton.setTeamUid(signInResponse.getTeamUID());
                                userSingleton.setUserName(signInResponse.getUserName());
                                view.toMain();
                            }
                        }

                        @Override
                        public void onError(Throwable t) {
                            NetworkUtil.networkErrorToast(t, User.class);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
        else
            view.toSignin();
    }
}
