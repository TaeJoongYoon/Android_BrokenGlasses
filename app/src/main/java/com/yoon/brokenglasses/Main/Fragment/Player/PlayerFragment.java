package com.yoon.brokenglasses.Main.Fragment.Player;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.yoon.brokenglasses.Main.MainActivity;
import com.yoon.brokenglasses.Model.SpinnerList;
import com.yoon.brokenglasses.R;
import com.yoon.brokenglasses.UserSingleton;
import com.yoon.brokenglasses.Util;
import com.yoon.brokenglasses.databinding.FragmentPlayerBinding;

public class PlayerFragment extends Fragment implements PlayerContract.View, AdapterView.OnItemSelectedListener {
    private FragmentPlayerBinding binding;
    private PlayerPresenter presenter;
    private UserSingleton userSingleton = UserSingleton.getInstance();

    private ArrayAdapter<String> jobAdapter;
    private ArrayAdapter<String> regionAdapter;
    private ArrayAdapter<String> defaultAdapter;

    public PlayerFragment() {
        presenter = new PlayerPresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public PlayerContract.Presenter getPresenter() {
        return presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_player,container,false);
        binding.setPlayer(this);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.userRequest(userSingleton.getUserUid());
    }

    @Override
    public void userSuccess() {
        view_info();
    }

    @Override
    public void userFailed() {
        view_request();
    }


    public void view_info(){
        binding.text.setVisibility(View.VISIBLE);
        binding.text.setText("성공");
    }

    public void view_request(){
        binding.request.setVisibility(View.VISIBLE);
        init();
        setListener();
        setClickListener();
    }

    public void init(){
        binding.personalName.setText(userSingleton.getUserName());

        jobAdapter = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item, SpinnerList.jobs);
        regionAdapter = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item, SpinnerList.regions);
        String[] df = {"상세선택"};
        defaultAdapter = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,df);

        binding.personalJobSpinner.setAdapter(jobAdapter);
        binding.personalMain1.setAdapter(regionAdapter);
        binding.personalMain2.setAdapter(regionAdapter);
        binding.personalMain3.setAdapter(regionAdapter);
    }

    public void setListener(){
        binding.personalJobSpinner.setOnItemSelectedListener(this);
        binding.personalMain1.setOnItemSelectedListener(this);
        binding.personalMain2.setOnItemSelectedListener(this);
        binding.personalMain3.setOnItemSelectedListener(this);
        binding.personalSub1.setOnItemSelectedListener(this);
        binding.personalSub2.setOnItemSelectedListener(this);
        binding.personalSub3.setOnItemSelectedListener(this);
    }

    public void setClickListener(){
        binding.btnPersonalRegister.setOnClickListener(view -> {
            binding.personalEtWeight.clearFocus();
            if(binding.personalEtBirthday.getText().toString().length()<=0)
                Util.makeToast("생일을 입력해주세요");
            else if(binding.personalEtHeight.getText().toString().length()<=0)
                Util.makeToast("키를 입력해주세요");
            else if(binding.personalEtWeight.getText().toString().length()<=0)
                Util.makeToast("몸무게를 입력해주세요");
            else if(!binding.personalJobSpinner.isSelected())
                Util.makeToast("직업을 선택해주세요");
            else if(binding.personalMain1.getSelectedItemPosition() == 0)
                Util.makeToast("지역을 선택해주세요");
            else
                presenter.register(binding);
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        binding.personalEtWeight.clearFocus();
        switch (adapterView.getId()){
            case R.id.personal_job_spinner:
                binding.personalJobSpinner.setSelected(true);
                break;
            case R.id.personal_main1:
                if(i==0) {
                    binding.personalSub1.setAdapter(defaultAdapter);
                    binding.personalSub1.setSelected(false);
                }
                else {
                    binding.personalMain1.setSelected(true);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, SpinnerList.subRegions[i - 1]);
                    binding.personalSub1.setAdapter(adapter);
                }
                break;
            case R.id.personal_main2:
                if(i==0) {
                    binding.personalSub2.setAdapter(defaultAdapter);
                    binding.personalSub2.setSelected(false);
                }
                else {
                    binding.personalMain2.setSelected(true);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, SpinnerList.subRegions[i - 1]);
                    binding.personalSub2.setAdapter(adapter);
                }
                break;
            case R.id.personal_main3:
                if(i==0) {
                    binding.personalSub3.setAdapter(defaultAdapter);
                    binding.personalSub3.setSelected(false);
                }
                else {
                    binding.personalMain3.setSelected(true);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, SpinnerList.subRegions[i - 1]);
                    binding.personalSub3.setAdapter(adapter);
                }
                break;
            case R.id.personal_sub1:
                binding.personalSub1.setSelected(true);
                break;
            case R.id.personal_sub2:
                binding.personalSub2.setSelected(true);
                break;
            case R.id.personal_sub3:
                binding.personalSub3.setSelected(true);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void success() {
        Util.makeToast("등록되었습니다!");
        binding.request.setVisibility(View.GONE);
        binding.text.setVisibility(View.VISIBLE);
        presenter.userRequest(userSingleton.getUserUid());
    }

    @Override
    public void failed() {
        Util.makeToast("데이터 등록에 실패했습니다!");
    }
}
