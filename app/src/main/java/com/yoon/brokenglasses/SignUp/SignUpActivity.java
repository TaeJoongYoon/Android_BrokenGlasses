package com.yoon.brokenglasses.SignUp;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.yoon.brokenglasses.R;
import com.yoon.brokenglasses.Util;
import com.yoon.brokenglasses.databinding.ActivitySignUpBinding;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View{

    private SignUpPresenter presenter;

    ActivitySignUpBinding binding;

    private TransitionDrawable email_trans;
    private TransitionDrawable pw_trans;

    private String email;
    private String userName;
    private String password;
    private String password_2;

    private boolean email_check = false;
    private boolean password_check = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        binding.setActivity(this);

        presenter = new SignUpPresenter(this);
        init();
    }

    public void init(){
        email_trans = (TransitionDrawable) getResources().getDrawable( R.drawable.sign_up_button_transition);
        pw_trans = (TransitionDrawable) getResources().getDrawable( R.drawable.sign_up_button_transition);

        binding.btnRequestEmail.setBackground(email_trans);
        binding.btnRequestPassword.setBackground(pw_trans);

        binding.requestID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                email_check = false;
                email_trans.startTransition(1000);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        binding.requestPW1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                password_check = false;
                binding.btnRequestSignup.setEnabled(false);
                pw_trans.startTransition(1000);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        binding.requestPW2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                password_check = false;
                binding.btnRequestSignup.setEnabled(false);
                pw_trans.startTransition(1000);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        binding.btnRequestEmail.setOnClickListener(view -> {
            email = binding.requestID.getText().toString();
            presenter.check_email(email);
        });
        binding.btnRequestPassword.setOnClickListener(view -> {
            password = binding.requestPW1.getText().toString();
            password_2 = binding.requestPW2.getText().toString();
            presenter.check_password(password,password_2);
        });
        binding.btnRequestSignup.setOnClickListener(view -> {
            userName = binding.requestNickname.getText().toString();
            presenter.call_sign_up(email,password,userName);
        });
    }

    @Override
    public void email_confirmed() {
        if(!email_check)
            email_trans.reverseTransition(1000);
        email_check = true;
        if(password_check)
            binding.btnRequestSignup.setEnabled(true);
    }

    @Override
    public void email_not_confirmed() {
        email_trans.startTransition(1000);
        email_check = false;
    }

    @Override
    public void password_confirmed() {
        if(!password_check)
            pw_trans.reverseTransition(1000);
        password_check = true;
        if(email_check)
            binding.btnRequestSignup.setEnabled(true);
    }

    @Override
    public void password_not_confirmed() {
        pw_trans.startTransition(1000);
        password_check = false;
    }

    @Override
    public void success() {
        Util.makeToast("회원가입 성공!");
        Intent returnIntent = new Intent();
        returnIntent.putExtra("ID",email);
        returnIntent.putExtra("PW",password);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    @Override
    public void failed() {
        Util.makeToast("회원가입 실패!");
    }
}
