package com.yoon.brokenglasses.Alarm;

import com.yoon.brokenglasses.Model.network.ListTeamJoinResponse;

import java.util.List;

/**
 * Created by Yoon on 2018-02-02.
 */

public class AlarmContract {
    interface View{
        void success(List<ListTeamJoinResponse> response);
        void failed();

        void confirm(ListTeamJoinResponse response);
        void not_confirm(ListTeamJoinResponse response);
    }
    interface Presenter{
        void request(String teamUID);
        void confirm(String teamUID, String userUID, boolean confirm);
    }
}
