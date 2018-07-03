package com.yoon.brokenglasses.Main.Fragment.Team;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.squareup.otto.Subscribe;
import com.yoon.brokenglasses.EventBus.ActivityResultEvent;
import com.yoon.brokenglasses.EventBus.BusProvider;
import com.yoon.brokenglasses.Search.SearchActivity;
import com.yoon.brokenglasses.R;
import com.yoon.brokenglasses.UserSingleton;
import com.yoon.brokenglasses.Util;
import com.yoon.brokenglasses.databinding.FragmentTeamBinding;


public class TeamFragment extends Fragment implements TeamContract.View,View.OnClickListener{
    private FragmentTeamBinding binding;
    private TeamPresenter presenter;
    private UserSingleton userSingleton = UserSingleton.getInstance();

    private ArrayAdapter<String> regionAdapter;
    private ArrayAdapter<String> birthAdapter;
    private ArrayAdapter<String> formationAdapter;
    private ArrayAdapter<String> defaultAdapter;

    private TransitionDrawable name_trans;
    private boolean name_check;

    public TeamFragment() {
        presenter = new TeamPresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public TeamContract.Presenter getPresenter() {
        return presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_team,container,false);
        binding.setTeam(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(userSingleton.getTeamUid() != null)
            presenter.request(userSingleton.getTeamUid());
        else
            init_buttons();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onDestroyView() {
        BusProvider.getInstance().unregister(this);
        super.onDestroyView();

    }

    @Override
    public void success() {
        init_view();
    }

    @Override
    public void failed() {
        Util.makeToast("데이터를 가져오는데 실패했습니다!");
    }

    public void init_view(){
        binding.text.setVisibility(View.VISIBLE);
        view_info();
    }

    public void init_buttons(){
        binding.teamButton.setVisibility(View.VISIBLE);
        view_buttons();
    }

    public void view_info(){
        binding.text.setText("성공");
    }

    public void view_buttons(){
        binding.teamCreate.setOnClickListener(this);
        binding.teamSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.team_create:
                Intent intent_register = new Intent(getActivity(), SearchActivity.class);
                intent_register.putExtra("option","register");
                getActivity().startActivityForResult(intent_register,Util.TEAM_REGISTER_CODE);
                break;
            case R.id.team_search:
                Intent intent_search = new Intent(getActivity(), SearchActivity.class);
                intent_search.putExtra("option","search");
                getActivity().startActivityForResult(intent_search,Util.TEAM_SEARCH_CODE);
                break;
        }
    }

    @Subscribe
    public void onActivityResultEvent(@NonNull ActivityResultEvent event) {
        onActivityResult(event.getRequestCode(), event.getResultCode(), event.getData());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Util.TEAM_SEARCH_CODE:
                if(resultCode == Activity.RESULT_OK) {
                    Util.makeToast("가입신청되었습니다!");
                }
                else
                    Util.makeToast("검색 실패!");
                break;
            case Util.TEAM_REGISTER_CODE:
                if(resultCode == Activity.RESULT_OK) {
                    binding.teamButton.setVisibility(View.GONE);
                    presenter.request(userSingleton.getTeamUid());
                    Util.makeToast("등록되었습니다!");
                }
                else
                    Util.makeToast("만들기 실패!");
                break;
        }
    }
}

