package com.yoon.brokenglasses.TeamDetail;

import com.yoon.brokenglasses.Model.network.TeamCheckResponse;
import com.yoon.brokenglasses.Model.network.TeamResponse;

/**
 * Created by Yoon on 2018-02-02.
 */

public class TeamDetailContract {
    interface View{
        void setView(TeamCheckResponse teamResponse);
        void view_finish();
    }
    interface Presenter{
        void teamDetail(String teamUID, String userUID);
        void teamJoin(String teamUID, String userUID);
    }
}
