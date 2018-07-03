package com.yoon.brokenglasses.Splash;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yoon.brokenglasses.Main.MainActivity;
import com.yoon.brokenglasses.R;
import com.yoon.brokenglasses.SignIn.SignInActivity;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    private SplashPresenter presenter;
    private SharedPreferences autoLogin;

    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splah);
        presenter = new SplashPresenter(this);

        init();
    }

    public void init(){
        autoLogin = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        email = autoLogin.getString("email",null);
        password = autoLogin.getString("password",null);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.check_signed(email, password);
    }
    @Override
    public void toSignin() {
        startActivity(new Intent(SplashActivity.this, SignInActivity.class));
        finish();
    }

    @Override
    public void toMain() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}
