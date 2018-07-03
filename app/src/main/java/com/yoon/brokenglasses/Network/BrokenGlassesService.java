package com.yoon.brokenglasses.Network;

import android.databinding.BindingAdapter;

import com.yoon.brokenglasses.Model.SearchResult;
import com.yoon.brokenglasses.Model.network.EmailPost;
import com.yoon.brokenglasses.Model.network.EmailResponse;
import com.yoon.brokenglasses.Model.network.ListTeamJoinPost;
import com.yoon.brokenglasses.Model.network.ListTeamJoinResponse;
import com.yoon.brokenglasses.Model.network.PersonalPositionPost;
import com.yoon.brokenglasses.Model.network.PersonalPositionResponse;
import com.yoon.brokenglasses.Model.network.SignInPost;
import com.yoon.brokenglasses.Model.network.SignInResponse;
import com.yoon.brokenglasses.Model.network.SignUpPost;
import com.yoon.brokenglasses.Model.network.SignUpResponse;
import com.yoon.brokenglasses.Model.network.TeamCheckPost;
import com.yoon.brokenglasses.Model.network.TeamCheckResponse;
import com.yoon.brokenglasses.Model.network.TeamConfirmPost;
import com.yoon.brokenglasses.Model.network.TeamConfirmResponse;
import com.yoon.brokenglasses.Model.network.TeamJoinPost;
import com.yoon.brokenglasses.Model.network.TeamJoinResponse;
import com.yoon.brokenglasses.Model.network.TeamNameCheckPost;
import com.yoon.brokenglasses.Model.network.TeamNameCheckResponse;
import com.yoon.brokenglasses.Model.network.TeamPositionPost;
import com.yoon.brokenglasses.Model.network.TeamPositionResponse;
import com.yoon.brokenglasses.Model.network.TeamPost;
import com.yoon.brokenglasses.Model.network.TeamResponse;
import com.yoon.brokenglasses.Model.network.TeamSearchPost;
import com.yoon.brokenglasses.Model.network.TeamSearchResponse;
import com.yoon.brokenglasses.Model.network.UserPost;
import com.yoon.brokenglasses.Model.network.UserResponse;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Yoon on 2017-12-27.
 */

public interface BrokenGlassesService {

    @POST("UserRegisterServlet")
    Flowable<EmailResponse> requestEmailConfirm(
            @Body EmailPost emailPost
            );

    @POST("UserRegisterServlet")
    Flowable<SignUpResponse> requestSignUp(
            @Body SignUpPost signUpPost
            );

    @POST("UserLoginServlet")
    Flowable<SignInResponse> requestSignIn(
            @Body SignInPost signInPost
    );

    @POST("UserRegisterServlet")
    Flowable<PersonalPositionResponse> requestPersonalPosition(
            @Body PersonalPositionPost personalPositionPost
            );

    @POST("UserServlet")
    Flowable<UserResponse> requestUser(
            @Body UserPost userPost
            );

    @POST("TeamRegisterServlet")
    Flowable<TeamNameCheckResponse> requestTeamName(
            @Body TeamNameCheckPost teamNameCheckPost
            );

    @POST("TeamRegisterServlet")
    Flowable<TeamPositionResponse> requestTeamPosition(
            @Body TeamPositionPost teamPositionPost
            );

    @POST("TeamServlet")
    Flowable<TeamResponse> requestTeam(
            @Body TeamPost teamPost
            );

    @POST("TeamSearchServlet")
    Flowable<List<TeamSearchResponse>> requestTeamSearch(
            @Body TeamSearchPost teamSearchPost
            );

    @POST("TeamServlet")
    Flowable<TeamCheckResponse> requestTeamCheck(
            @Body TeamCheckPost teamCheckPost
            );
    @POST("ReqTeamJoinServlet")
    Flowable<TeamJoinResponse> requestJoin(
            @Body TeamJoinPost teamJoinPost
            );

    @POST("ReqTeamJoinServlet")
    Flowable<List<ListTeamJoinResponse>> requestListTeamJoin(
            @Body ListTeamJoinPost teamJoinPost
            );

    @POST("ReqTeamJoinServlet")
    Flowable<TeamConfirmResponse> requestTeamConfirm(
            @Body TeamConfirmPost teamConfirmPost
            );
}
