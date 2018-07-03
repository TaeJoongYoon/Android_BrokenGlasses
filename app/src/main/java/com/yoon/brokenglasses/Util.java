package com.yoon.brokenglasses;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yoon on 2017-12-27.
 */

public class Util {

    public static final int TEAM_REGISTER_CODE = 1111;
    public static final int TEAM_SEARCH_CODE   = 1112;
    public static final int TEAM_DETAIL_CODE   = 1113;

    public static void makeToast(String text){
        Toast.makeText(MyApplication.getAppContext(),text, Toast.LENGTH_SHORT).show();
    }
}
