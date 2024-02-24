package com.example.webthingscontrollerapp.view.viewmodel;

import static com.example.webthingscontrollerapp.view.MainActivity.LOG_STRING;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.webthingscontrollerapp.view.MainActivity;
import com.example.webthingscontrollerapp.view.viewmodel.model.ThingRepository;
import com.example.webthingscontrollerapp.view.viewmodel.model.pojo.Property;
import com.example.webthingscontrollerapp.view.viewmodel.model.pojo.Thing;

import java.util.List;
import java.util.Map;

public class ThingViewModel extends ViewModel {
    private final MutableLiveData<List<Thing>> things = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    private final MutableLiveData<Property> alteredProperty = new MutableLiveData<>();
    private final ThingRepository repository;

    public ThingViewModel() {
        repository = new ThingRepository();
    }

    public LiveData<List<Thing>> getThings() {
        return things;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<Property> getAlteredProperty() {
        return alteredProperty;
    }



    public void loadThings() {
        Log.d(LOG_STRING, "ThingViewModel loadThings()");

        isLoading.setValue(true);
        try {
            repository.getThings(new ThingRepository.DataLoadListener() {
                @Override
                public void onDataLoaded(List<Thing> thingList) {
                    Log.d(LOG_STRING, "ThingViewModel onDataLoaded()");
                    loadThingValues(thingList);
                }

                @Override
                public void onDataLoadFailed() {
                    Log.d(LOG_STRING, "ThingViewModel onDataLoadFailed()");
                    isLoading.postValue(false);
                }
            });
        } catch (Exception e) {
            Log.d(LOG_STRING, "ThingViewModel EXCEPTION " + e);
        }
    }

    private void loadThingValues(List<Thing> skeletonThingsList) {
        Log.d(LOG_STRING, "ThingViewModel loadThingValues()");

        if (skeletonThingsList == null || skeletonThingsList.isEmpty()) {
            Log.d(LOG_STRING, "ThingViewModel loadThings() Things list is empty or null");
            return;
        }

        final int[] pendingRequests = {skeletonThingsList.size()};

        for (Thing t : skeletonThingsList) {
            repository.getThingProperties(new ThingRepository.ThingPropertiesLoadListener() {
                @Override
                public void onPropertiesLoaded(Map<String, Object> propertyValues) {
                    Log.d(LOG_STRING, "ThingViewModel loadThings() setting Thing properties");

                    t.setPropertyValues(propertyValues);
                    t.setChildParentIds();

                    pendingRequests[0]--;
                    if (pendingRequests[0] == 0) {
                        Log.d(LOG_STRING, "ThingViewModel loadThings() updating mutable list");
                        isLoading.postValue(false);
                        things.postValue(skeletonThingsList);
                    }
                }

                @Override
                public void onPropertiesLoadFailed() {
                    Log.d(LOG_STRING, "ThingViewModel loadThings() failed to load properties");

                    pendingRequests[0]--;
                    if (pendingRequests[0] == 0) {
                        Log.d(LOG_STRING, "ThingViewModel loadThings() updating mutable list after fail");
                        things.postValue(skeletonThingsList);
                        isLoading.postValue(false);
                    }
                }
            }, t.getPropertiesUrl());
        }
    }

    public void updateThingProperty(String newValue, Property property) {
        Log.d(LOG_STRING, "ThingViewModel updateThingProperty() ");

        Object updatedValue;

        if (!newValue.isEmpty()){
            switch (property.getType()) {
                case "number":
                case "integer":
                    updatedValue = Double.parseDouble(newValue);
                    break;
                case "boolean":
                    updatedValue = Boolean.parseBoolean(newValue);
                    break;
                case "string":
                    updatedValue = newValue;
                    break;
                default:
                    Log.d(LOG_STRING, "ThingViewModel updateThingProperty() object not of known type?: " + property.getType());
                    return;
            }

            repository.setThingPropertyValue(property.getPropertyUrl(), updatedValue, property.getName(), new ThingRepository.PropertyUpdateListener() {
                @Override
                public void onSuccessfulResponse() {
                    Log.d(LOG_STRING, "ThingViewModel updateThingProperty() succeeded, reloading ");
//                    loadThings();
                    List<Thing> temp = things.getValue();

                    alteredProperty.postValue(property);
                }

                @Override
                public void onFailedResponse() {
                    Log.d(LOG_STRING, "ThingViewModel updateThingProperty() failed to post value");

                }
            });
        }

    }
}

