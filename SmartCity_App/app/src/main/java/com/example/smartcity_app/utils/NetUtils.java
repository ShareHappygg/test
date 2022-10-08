package com.example.smartcity_app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

//import com.example.smartcity_app.application.BaseApplication;

import com.example.smartcity_app.BaseActivity;
import com.example.smartcity_app.application.BaseApplication;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetUtils {


    private  static OkHttpClient okHttpClient =getInstance();

    private static OkHttpClient getInstance() {
        return  new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences("token", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("token", "");
                Log.i("token",token);
                Request request = chain.request().newBuilder().addHeader("Authorization","").build();
                return  chain.proceed(request);
            }
        }).build();
    }

    public  static  void doGet(String url, Callback callback)
    {
        Request request = new Request.Builder().url(url).get().build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public  static  void doPost(String url, RequestBody body, Callback callback)
    {
        Request request = new Request.Builder().url(url).post(body).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
    public  static  void getImage(String url, Callback callback)
    {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
