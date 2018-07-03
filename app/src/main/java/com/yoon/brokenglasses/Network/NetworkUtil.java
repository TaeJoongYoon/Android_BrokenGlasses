package com.yoon.brokenglasses.Network;

import android.location.Location;
import android.util.Log;

import com.google.gson.Gson;
import com.yoon.brokenglasses.Util;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Yoon on 2017-12-27.
 */

public class NetworkUtil {

    public static String baseUrl = "http://www.fblens.com/servlet/";

    private static OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        return builder.build();
    }

    public static BrokenGlassesService brokenGlassesServiceRequest(){
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(createOkHttpClient())
                .build()
                .create(BrokenGlassesService.class);
    }

    public static <T> void networkErrorToast(Throwable t, Class<T> classOfT){
        try{
            ResponseBody responseBody = ((retrofit2.HttpException)t).response().errorBody();
            if(((HttpException)t).code()/100 == 4){
                try {
                    T response = new Gson().fromJson(responseBody.string(), classOfT);
                    Log.e("",(String)response.getClass().getMethod("getMessage",(Class<?>[])null).invoke(response));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                t.printStackTrace();
            }
        }catch (Exception e){
            Util.makeToast("네트워크 오류입니다.");
        }

    }
}
