package com.yoon.brokenglasses.Search;

import com.yoon.brokenglasses.Model.network.TeamSearchResponse;
import com.yoon.brokenglasses.databinding.ActivitySearchBinding;

import java.util.List;

/**
 * Created by Yoon on 2018-02-02.
 */

public class SearchContract {
    public interface View{
        void name_chk_success();
        void name_chk_failed();
        void register_success();
        void register_failed();

        void search_success(List<TeamSearchResponse> responseList);
        void search_failed();

        void start_detail(String teamUID);
    }
    interface Presenter{
        void register_name_chk(String teamName);
        void register(ActivitySearchBinding binding);

        void search(String teamName);
    }
}
