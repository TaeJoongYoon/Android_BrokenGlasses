package com.yoon.brokenglasses.SignIn;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.yoon.brokenglasses.Main.MainActivity;
import com.yoon.brokenglasses.R;
import com.yoon.brokenglasses.SignUp.SignUpActivity;
import com.yoon.brokenglasses.Util;
import com.yoon.brokenglasses.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity implements SignInContract.View {

    private SignInPresenter presenter;

    ActivitySignInBinding binding;

    private String email;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);
        binding.setActivity(this);

        presenter = new SignInPresenter(this);
        init();
    }

    public void init(){
        binding.PW.setImeOptions(EditorInfo.IME_ACTION_DONE);
        binding.PW.setOnEditorActionListener((textView, i, keyEvent) -> {
            if(i == EditorInfo.IME_ACTION_DONE) {
                btn_call();
            }
            return true;
        });
        binding.btnSignin.setOnClickListener(view -> {
            btn_call();
        });

        binding.txtSignup.setOnClickListener(view -> startActivityForResult(new Intent(SignInActivity.this,SignUpActivity.class),1));
    }

    public void btn_call(){
        email = binding.ID.getText().toString();
        password = binding.PW.getText().toString();
        if(email.length() <= 0)
            Util.makeToast("아이디를 입력해주세요!");
        else if(password.length() == 0)
            Util.makeToast("비밀번호를 입력해주세요!");
        else if(password.length() < 8)
            Util.makeToast("8글자 이상 입력해주세요!");
        else {
            presenter.call_sign_in(email, password);
            binding.signInProgress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void success(String email, String password) {
        Util.makeToast("로그인 성공!");
        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        SharedPreferences.Editor autoLogin = auto.edit();
        autoLogin.clear();
        autoLogin.putString("email", email);
        autoLogin.putString("password", password);
        autoLogin.apply();

        startActivity(new Intent(SignInActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void failed() {
        Util.makeToast("회원정보가 없습니다. 회원가입 해주세요!");
        binding.signInProgress.setVisibility(View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                email = data.getStringExtra("ID");
                password = data.getStringExtra("PW");
                binding.ID.setText(email);
                binding.PW.setText(password);
                binding.signInProgress.setVisibility(View.VISIBLE);

                presenter.call_sign_in(email,password);
            }
            else
                return;
        }
    }
}
