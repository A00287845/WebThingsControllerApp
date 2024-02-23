package com.example.webthingscontrollerapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThingRepository {
    private WebThingsService webThingsService;

    public ThingRepository() {
        webThingsService = WebThingsClient.getInstance();
    }

    public void getThings(final DataLoadListener listener) {
        webThingsService.listThings().enqueue(new Callback<retrofit2.Response<List<Thing>>>() {

            @Override
            public void onResponse(Call<Response<List<Thing>>> call, Response<Response<List<Thing>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Successfully received a list of things
                    List<Thing> things = response.body().body();
                    // Proceed to use 'things'
                } else {
                    // Handle the case where the response is not successful
                }
            }

            @Override
            public void onFailure(Call<retrofit2.Response<List<Thing>>> call, Throwable t) {
                // Handle failure, e.g., network error or exception
            }
        });
    }

    public interface DataLoadListener {
        void onDataLoaded(List<Thing> things);
        void onDataLoadFailed();
    }
}

