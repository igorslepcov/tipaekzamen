package com.example.tipaekzamen;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).baseUrl("http://cinema.areas.su").build();

        return retrofit;
    }

    public static com.example.tipaekzamen.ApiService getService(){
        com.example.tipaekzamen.ApiService apiService = ApiClient.getRetrofit().create(com.example.tipaekzamen.ApiService.class);

        return apiService;
    }

}
