package com.yoon.brokenglasses.Main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.yoon.brokenglasses.Main.Fragment.Home.HomeFragment;
import com.yoon.brokenglasses.Main.Fragment.Match.MatchFragment;
import com.yoon.brokenglasses.Main.Fragment.Player.PlayerFragment;
import com.yoon.brokenglasses.Main.Fragment.Team.TeamFragment;

/**
 * Created by Yoon on 2017-12-27.
 */

public class MainTapPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    private HomeFragment homeFragment;
    private TeamFragment teamFragment;
    private PlayerFragment playerFragment;
    private MatchFragment matchFragment;

    public MainTapPagerAdapter(FragmentManager fm, int tabCount,
                                                    HomeFragment homeFragment,
                                                    TeamFragment teamFragment,
                                                    PlayerFragment playerFragment,
                                                    MatchFragment matchFragment) {
        super(fm);
        this.tabCount = tabCount;
        this.homeFragment = homeFragment;
        this.teamFragment = teamFragment;
        this.playerFragment = playerFragment;
        this.matchFragment = matchFragment;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return homeFragment;
            case 1:
                return teamFragment;
            case 2:
                return playerFragment;
            case 3:
                return matchFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    public HomeFragment getHomeFragment() {
        return homeFragment;
    }

    public TeamFragment getTeamFragment() {
        return teamFragment;
    }

    public PlayerFragment getPlayerFragment() {
        return playerFragment;
    }

    public MatchFragment getMatchFragment() {
        return matchFragment;
    }
}
