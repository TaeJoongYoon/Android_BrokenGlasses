package com.yoon.brokenglasses.Search;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.yoon.brokenglasses.Model.SpinnerList;
import com.yoon.brokenglasses.Model.network.TeamSearchResponse;
import com.yoon.brokenglasses.R;
import com.yoon.brokenglasses.TeamDetail.TeamDetailActivity;
import com.yoon.brokenglasses.UserSingleton;
import com.yoon.brokenglasses.Util;
import com.yoon.brokenglasses.databinding.ActivitySearchBinding;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchContract.View, AdapterView.OnItemSelectedListener {
    private ActivitySearchBinding binding;
    private SearchPresenter presenter;
    private SearchRecyclerViewAdapter adapter;

    private String OPTION;

    private ArrayAdapter<String> regionAdapter;
    private ArrayAdapter<String> birthAdapter;
    private ArrayAdapter<String> formationAdapter;
    private ArrayAdapter<String> defaultAdapter;

    private TransitionDrawable name_trans;
    private boolean name_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        binding.setActivity(this);
        presenter = new SearchPresenter(this);

        init();
    }

    public void init(){
        Intent intent = getIntent();
        OPTION = intent.getStringExtra("option");

        if(OPTION.equals("register"))
            view_register();
        else if(OPTION.equals("search"))
            view_search();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////// register
    public void view_register(){
        binding.teamRegisterView.setVisibility(View.VISIBLE);
        init_register();
        setAdapter_register();
        setClickListener_register();
    }

    public void init_register(){
        name_check = false;
        name_trans = (TransitionDrawable) getResources().getDrawable( R.drawable.sign_up_button_transition);
        binding.teamRegisterNameCheck.setBackground(name_trans);

        binding.teamRegisterName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                name_check = false;
                name_trans.startTransition(1000);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        regionAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, SpinnerList.regions);
        birthAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, SpinnerList.birth);
        formationAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, SpinnerList.formation);
        String[] df = {"상세선택"};
        defaultAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,df);

        binding.teamRegisterMain1.setAdapter(regionAdapter);
        binding.teamRegisterMain2.setAdapter(regionAdapter);
        binding.teamRegisterMain3.setAdapter(regionAdapter);

        binding.teamRegisterBirth.setAdapter(birthAdapter);

        binding.teamRegisterFm1.setAdapter(formationAdapter);
        binding.teamRegisterFm2.setAdapter(formationAdapter);
        binding.teamRegisterFm3.setAdapter(formationAdapter);
    }

    public void setAdapter_register(){

        binding.teamRegisterMain1.setOnItemSelectedListener(this);
        binding.teamRegisterMain2.setOnItemSelectedListener(this);
        binding.teamRegisterMain3.setOnItemSelectedListener(this);
        binding.teamRegisterSub1.setOnItemSelectedListener(this);
        binding.teamRegisterSub2.setOnItemSelectedListener(this);
        binding.teamRegisterSub3.setOnItemSelectedListener(this);

        binding.teamRegisterBirth.setOnItemSelectedListener(this);

        binding.teamRegisterFm1.setOnItemSelectedListener(this);
        binding.teamRegisterFm2.setOnItemSelectedListener(this);
        binding.teamRegisterFm3.setOnItemSelectedListener(this);
    }


    public void setClickListener_register(){
        binding.teamRegisterNameCheck.setOnClickListener(view -> {
            binding.teamRegisterName.clearFocus();
            if(binding.teamRegisterName.getText().toString().length() > 0)
                presenter.register_name_chk(binding.teamRegisterName.getText().toString());
            else
                Util.makeToast("팀 이름을 입력해주세요!");
        });
        binding.btnTeamRegister.setOnClickListener(view -> {
            binding.teamRegisterName.clearFocus();
            if(binding.teamRegisterName.getText().toString().length()<=0)
                Util.makeToast("팀 이름을 입력해주세요");
            else if(!name_check)
                Util.makeToast("팀 이름을 확인해주세요");
            else if(binding.teamRegisterMain1.getSelectedItemPosition() == 0)
                Util.makeToast("지역을 선택해주세요");
            else if(binding.teamRegisterFm1.getSelectedItemPosition() == 0)
                Util.makeToast("팀 포메이션을 선택해주세요");
            else
                presenter.register(binding);
        });
    }
    //////////////////////////////////////////////////////////////////////////////////////////////// register

    //////////////////////////////////////////////////////////////////////////////////////////////// search
    public void view_search(){
        binding.teamSearchView.setVisibility(View.VISIBLE);
        init_search();
        setRecyclerView();
    }

    public void init_search(){
        binding.teamSearchBtn.setOnClickListener(view -> {
            presenter.search(binding.teamSearchEt.getText().toString());
        });
    }

    public void setRecyclerView(){
        binding.teamSearchRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new SearchRecyclerViewAdapter(this,this);
        binding.teamSearchRecyclerView.setAdapter(adapter);
    }

    @Override
    public void start_detail(String teamUID) {
        Intent intent = new Intent(this, TeamDetailActivity.class);
        intent.putExtra("UID",teamUID);
        startActivityForResult(intent,Util.TEAM_DETAIL_CODE);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////// search


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        binding.teamRegisterName.clearFocus();
        switch (adapterView.getId()) {
            case R.id.team_register_main1:
                if (i == 0) {
                    binding.teamRegisterSub1.setAdapter(defaultAdapter);
                    binding.teamRegisterSub1.setSelected(false);
                } else {
                    binding.teamRegisterMain1.setSelected(true);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, SpinnerList.subRegions[i - 1]);
                    binding.teamRegisterSub1.setAdapter(adapter);
                }
                break;
            case R.id.team_register_main2:
                if (i == 0) {
                    binding.teamRegisterSub2.setAdapter(defaultAdapter);
                    binding.teamRegisterSub2.setSelected(false);
                } else {
                    binding.teamRegisterMain1.setSelected(true);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, SpinnerList.subRegions[i - 1]);
                    binding.teamRegisterSub2.setAdapter(adapter);
                }
                break;
            case R.id.team_register_main3:
                if (i == 0) {
                    binding.teamRegisterSub3.setAdapter(defaultAdapter);
                    binding.teamRegisterSub3.setSelected(false);
                } else {
                    binding.teamRegisterMain1.setSelected(true);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, SpinnerList.subRegions[i - 1]);
                    binding.teamRegisterSub3.setAdapter(adapter);
                }
                break;
            case R.id.team_register_sub1:
                binding.teamRegisterSub1.setSelected(true);
                break;
            case R.id.team_register_sub2:
                binding.teamRegisterSub2.setSelected(true);
                break;
            case R.id.team_register_sub3:
                binding.teamRegisterSub3.setSelected(true);
                break;
            case R.id.team_register_birth:
                binding.teamRegisterBirth.setSelected(true);
                break;
            case R.id.team_register_fm1:
                binding.teamRegisterFm1.setSelected(true);
                break;
            case R.id.team_register_fm2:
                binding.teamRegisterFm2.setSelected(true);
                break;
            case R.id.team_register_fm3:
                binding.teamRegisterFm3.setSelected(true);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void name_chk_success() {
        if(!name_check)
            name_trans.reverseTransition(1000);
        name_check = true;
        Util.makeToast("가능한 팀 이름입니다!");
    }

    @Override
    public void name_chk_failed() {
        if(name_check)
            name_trans.startTransition(1000);
        name_check = false;
        Util.makeToast("이미 존재하는 팀 이름입니다!");
    }

    @Override
    public void register_success() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    @Override
    public void register_failed() {
        Util.makeToast("데이터를 등록하는데 실패했습니다!");
    }

    @Override
    public void search_success(List<TeamSearchResponse> responses) {
        adapter.addItems(responses);
    }

    @Override
    public void search_failed() {
        Util.makeToast("검색에 실패했습니다!");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case Util.TEAM_DETAIL_CODE:
                if(resultCode == Activity.RESULT_OK) {
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                }
                break;
        }
    }

}
