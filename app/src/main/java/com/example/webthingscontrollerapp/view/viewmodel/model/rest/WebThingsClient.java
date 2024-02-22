package com.example.webthingscontrollerapp.view.viewmodel.model.rest;

import static com.example.webthingscontrollerapp.view.MainActivity.LOG_STRING;

import android.util.Log;


import com.example.webthingscontrollerapp.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebThingsClient {

    private static final String BASE_URL = "https://a00287845.webthings.io";
    private static WebThingsService webThingsService;

    public static WebThingsService getInstance() {
        if (webThingsService == null) {
            OkHttpClient clientWithAuth = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        Request original = chain.request();
                        Log.d(LOG_STRING, "Request URL: " + original.url());
                        Request.Builder requestBuilder = original.newBuilder()
                                .header("Authorization", "Bearer " + BuildConfig.BEARER_TOKEN)
                                .header("Accept", "application/json")
                                .method(original.method(), original.body());
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    })
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .client(clientWithAuth)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            webThingsService = retrofit.create(WebThingsService.class);
        }
        return webThingsService;
    }
}
