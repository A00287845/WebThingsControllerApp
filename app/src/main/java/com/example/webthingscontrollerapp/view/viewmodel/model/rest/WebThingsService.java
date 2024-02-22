package com.example.webthingscontrollerapp.view.viewmodel.model.rest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WebThingsService {
    @GET("/things")
    Call<ResponseBody> listThings();

    @GET
    Call<ResponseBody> mapOfThingsProperties(@Url String url);
}
