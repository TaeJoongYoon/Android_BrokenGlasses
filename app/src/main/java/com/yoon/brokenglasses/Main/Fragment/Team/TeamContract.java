package com.yoon.brokenglasses.Main.Fragment.Team;

import com.yoon.brokenglasses.Main.Fragment.Home.HomeContract;
import com.yoon.brokenglasses.databinding.FragmentTeamBinding;

/**
 * Created by Yoon on 2017-12-27.
 */

public class TeamContract {
    interface View{
        Presenter getPresenter();
        void success();
        void failed();
    }

    public interface Presenter{
        void request(String teamUID);
    }
}
