package com.example.webthingscontrollerapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebThingsClient {

    private static final String BASE_URL = "https://your.api.url/"; // Change to your base URL
    private static WebThingsService webThingsService;

    public static WebThingsService getInstance() {
        if (webThingsService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            webThingsService = retrofit.create(WebThingsService.class);
        }
        return webThingsService;
    }
}
