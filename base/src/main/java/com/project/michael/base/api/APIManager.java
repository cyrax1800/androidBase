package com.project.michael.base.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.michael.base.utils.Settings;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by michael on 1/30/17.
 */

public class APIManager {

    private static String TAG = "tmp-ApiManager";

    private static Retrofit retrofit = null;

    public static void SetUpRetrofit(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        if(Settings.getRetrofitAPIUrl().isEmpty())
            throw new ExceptionInInitializerError("Api Url must be define in Settings.json file");

        retrofit = new Retrofit.Builder()
                .baseUrl(Settings.getRetrofitAPIUrl())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private static Retrofit getclient(){
        if(!Settings.isUsingRetrofitAPI())
            throw new NullPointerException("Must Enable using Retrofit in Settings.json file");

        if(retrofit == null)
            SetUpRetrofit();

        return retrofit;
    }
}
