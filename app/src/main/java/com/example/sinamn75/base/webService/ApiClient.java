package com.example.sinamn75.base.webService;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

    public static Retrofit getClient() {
        okHttpBuilder.addInterceptor(chain -> {
            Request request = chain.request();
            Request.Builder newRequest = request.newBuilder().header("Accept", "application/json");
            return chain.proceed(newRequest.build());
        });
        return new Retrofit.Builder().baseUrl("BASE_URL").client(okHttpBuilder.build()).addConverterFactory(GsonConverterFactory.create()).build();
    }
}