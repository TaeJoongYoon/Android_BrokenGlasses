package com.yoon.brokenglasses.Main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.yoon.brokenglasses.Alarm.AlarmActivity;
import com.yoon.brokenglasses.EventBus.ActivityResultEvent;
import com.yoon.brokenglasses.EventBus.BusProvider;
import com.yoon.brokenglasses.Main.Fragment.Home.HomeContract;
import com.yoon.brokenglasses.Main.Fragment.Home.HomeFragment;
import com.yoon.brokenglasses.Main.Fragment.Match.MatchContract;
import com.yoon.brokenglasses.Main.Fragment.Match.MatchFragment;
import com.yoon.brokenglasses.Main.Fragment.Player.PlayerContract;
import com.yoon.brokenglasses.Main.Fragment.Player.PlayerFragment;
import com.yoon.brokenglasses.Main.Fragment.Team.TeamContract;
import com.yoon.brokenglasses.Main.Fragment.Team.TeamFragment;
import com.yoon.brokenglasses.R;
import com.yoon.brokenglasses.SignIn.SignInActivity;
import com.yoon.brokenglasses.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private MainTapPagerAdapter mainTapPagerAdapter;

    private HomeFragment homeFragment;
    private TeamFragment teamFragment;
    private PlayerFragment playerFragment;
    private MatchFragment matchFragment;

    private HomeContract.Presenter homePresenter;
    private TeamContract.Presenter teamPresenter;
    private PlayerContract.Presenter playerPresenter;
    private MatchContract.Presenter matchPresenter;

    private SharedPreferences autoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setActivity(this);

        initFragment();
        initPresenters();
        initToolbar();
        initTabLayout();
        initViewPager();
    }


    public void initFragment(){
        homeFragment = new HomeFragment();
        teamFragment = new TeamFragment();
        playerFragment = new PlayerFragment();
        matchFragment = new MatchFragment();
    }

    public void initPresenters(){
        homePresenter = homeFragment.getPresenter();
        teamPresenter = teamFragment.getPresenter();
        playerPresenter = playerFragment.getPresenter();
        matchPresenter = matchFragment.getPresenter();
    }

    public void initToolbar(){
        setSupportActionBar(binding.mainToolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_48dp);
        getSupportActionBar().setTitle(null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.main_alarm:
                Intent intent = new Intent(this, AlarmActivity.class);
                startActivity(intent);
                break;
            case R.id.main_logout:
                autoLogin = getSharedPreferences("auto",MODE_PRIVATE);
                SharedPreferences.Editor editor = autoLogin.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(this, SignInActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void initTabLayout(){
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("홈"));
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("팀"));
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("개인"));
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("경기"));

        binding.mainTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        binding.mainTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.mainPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void initViewPager(){
        mainTapPagerAdapter = new MainTapPagerAdapter(getSupportFragmentManager(), binding.mainTabLayout.getTabCount(),
                homeFragment,
                teamFragment,
                playerFragment,
                matchFragment);
        binding.mainPager.setAdapter(mainTapPagerAdapter);
        binding.mainPager.setOffscreenPageLimit(4);
        binding.mainPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.mainTabLayout));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        BusProvider.getInstance().post(new ActivityResultEvent(requestCode, resultCode, data));
    }

}
