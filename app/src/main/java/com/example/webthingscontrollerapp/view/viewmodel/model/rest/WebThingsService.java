package com.example.webthingscontrollerapp.view.viewmodel.model.rest;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Url;

public interface WebThingsService {
    @GET("/things")
    Call<ResponseBody> listThings();

    @GET
    Call<ResponseBody> getMapOfThingsProperties(@Url String url);

    @PUT
    Call<ResponseBody> postPropertyValue(@Url String url, @Body Map<String, Object> jsonBody);
}
