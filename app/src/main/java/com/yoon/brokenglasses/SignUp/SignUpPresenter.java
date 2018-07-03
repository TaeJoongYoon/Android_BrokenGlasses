package com.yoon.brokenglasses.SignUp;

import com.yoon.brokenglasses.Model.network.EmailPost;
import com.yoon.brokenglasses.Model.network.EmailResponse;
import com.yoon.brokenglasses.Model.network.SignUpPost;
import com.yoon.brokenglasses.Model.User;
import com.yoon.brokenglasses.Model.network.SignUpResponse;
import com.yoon.brokenglasses.Network.NetworkUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by Yoon on 2017-12-27.
 */

public class SignUpPresenter implements SignUpContract.Presenter {

    private SignUpContract.View view;

    public SignUpPresenter(SignUpContract.View view){
        this.view = view;
    }

    @Override
    public void check_email(String email) {
        NetworkUtil.brokenGlassesServiceRequest()
                .requestEmailConfirm(new EmailPost(email))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<EmailResponse>() {
                    @Override
                    public void onNext(EmailResponse emailResponse) {
                        if(emailResponse.isError())
                            view.email_not_confirmed();
                        else
                            view.email_confirmed();
                    }

                    @Override
                    public void onError(Throwable t) {
                        NetworkUtil.networkErrorToast(t,User.class);
                        view.failed();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void check_password(String password, String password_2) {
        if (password.equals(password))
            view.password_confirmed();
        else
            view.password_not_confirmed();
    }

    @Override
    public void call_sign_up(String email, String password, String userName) {
        NetworkUtil.brokenGlassesServiceRequest()
                .requestSignUp(new SignUpPost(email, password, userName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceSubscriber<SignUpResponse>() {
                    @Override
                    public void onNext(SignUpResponse signUpResponse) {
                        if(signUpResponse.isError())
                            view.failed();
                        else
                            view.success();
                    }

                    @Override
                    public void onError(Throwable t) {
                        NetworkUtil.networkErrorToast(t, User.class);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("complete");
                    }
                });
    }
}
