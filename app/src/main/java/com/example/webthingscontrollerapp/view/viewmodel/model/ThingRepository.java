package com.example.webthingscontrollerapp.view.viewmodel.model;

import static com.example.webthingscontrollerapp.view.MainActivity.LOG_STRING;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.webthingscontrollerapp.view.viewmodel.model.rest.WebThingsClient;
import com.example.webthingscontrollerapp.view.viewmodel.model.rest.WebThingsService;
import com.example.webthingscontrollerapp.view.viewmodel.model.pojo.Thing;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThingRepository {
    private final WebThingsService webThingsService;

    public ThingRepository() {
        webThingsService = WebThingsClient.getInstance();
    }

    public void getThings(final DataLoadListener listener) {
        Log.d(LOG_STRING, "Thing repository getThings()");
        Call<ResponseBody> call = webThingsService.listThings();

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        Log.d(LOG_STRING, "Thing repository onResponse getThings SUCCESSFUL()");
                        String jsonResponse = response.body().string();
                        Log.d(LOG_STRING, "Thing repository onResponse getThings bodytext: " + jsonResponse);

                        Gson gson = new Gson();
                        Type listOfThingsType = new TypeToken<List<Thing>>() {
                        }.getType();
                        List<Thing> things = gson.fromJson(jsonResponse, listOfThingsType);
                        listener.onDataLoaded(things);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                } else {
                    Log.d(LOG_STRING, "Response not successful: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.e(LOG_STRING, "Failed to get things", t);
                listener.onDataLoadFailed();
            }
        });
    }

    public void getThingProperties(final ThingPropertiesLoadListener listener, String propertiesUrl) {
        Log.d(LOG_STRING, "Thing repository getThingProperties()");
        Call<ResponseBody> call = webThingsService.getMapOfThingsProperties(propertiesUrl);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        String jsonResponse = response.body().string();
                        Log.d(LOG_STRING, "Thing repository onResponse getThingProperties bodytext: " + jsonResponse);

                        Gson gson = new Gson();
                        Type mapOfPropertyValuesType = new TypeToken<Map<String, Object>>() {
                        }.getType();
                        Map<String, Object> propertyValues = gson.fromJson(jsonResponse, mapOfPropertyValuesType);

                        listener.onPropertiesLoaded(propertyValues);
                    } catch (IOException e) {
                        Log.e(LOG_STRING, "Error reading response body", e);
                        listener.onPropertiesLoadFailed();
                    }
                } else {
                    Log.d(LOG_STRING, "Response not successful: " + response.code() + " - " + response.message());
                    listener.onPropertiesLoadFailed();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.e(LOG_STRING, "Failed to get thing properties", t);
                listener.onPropertiesLoadFailed();
            }
        });
    }

    public void setThingPropertyValue(String url, Object value, String propertyToUpdate, PropertyUpdateListener listener){

        Log.d(LOG_STRING, "Thing repository setThingPropertyValue()");
        Call<ResponseBody> call = webThingsService.postPropertyValue(url, Map.of(propertyToUpdate, value));

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        String jsonResponse = response.body().string();
                        Log.d(LOG_STRING, "Thing repository onResponse setThingPropertyValue bodytext: " + jsonResponse);
                        listener.onSuccessfulResponse();

                    } catch (IOException e) {
                        Log.e(LOG_STRING, "Error reading response body", e);
                        listener.onFailedResponse();
                    }
                } else {
                    Log.d(LOG_STRING, "Response not successful: " + response.code() + " - " + response.message());
                    listener.onFailedResponse();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.e(LOG_STRING, "Failed to post property value", t);
//                listener.onPropertiesLoadFailed();
            }
        });

    }

    public interface PropertyUpdateListener{
        void onSuccessfulResponse();
        void onFailedResponse();
    }

    public interface DataLoadListener {
        void onDataLoaded(List<Thing> things);
        void onDataLoadFailed();
    }

    public interface ThingPropertiesLoadListener {
        void onPropertiesLoaded(Map<String, Object> properties);
        void onPropertiesLoadFailed();
    }
}

