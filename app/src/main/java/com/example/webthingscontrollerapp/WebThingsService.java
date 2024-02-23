package com.example.webthingscontrollerapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.Response;
import java.util.List;

public interface WebThingsService {
    @GET("things")
    Call<Response<List<Thing>>> listThings();
}
