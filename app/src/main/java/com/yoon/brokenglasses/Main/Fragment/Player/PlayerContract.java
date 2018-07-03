package com.yoon.brokenglasses.Main.Fragment.Player;

import com.yoon.brokenglasses.Main.Fragment.Home.HomeContract;
import com.yoon.brokenglasses.databinding.FragmentPlayerBinding;

/**
 * Created by Yoon on 2017-12-27.
 */

public class PlayerContract {
    interface View{
        Presenter getPresenter();
        void userSuccess();
        void userFailed();
        void success();
        void failed();
    }
    public interface Presenter{
        void userRequest(String userUID);
        void register(FragmentPlayerBinding binding);
    }
}
