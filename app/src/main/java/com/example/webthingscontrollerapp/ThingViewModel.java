package com.example.webthingscontrollerapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ThingViewModel extends ViewModel {
    private MutableLiveData<List<Thing>> things = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private ThingRepository repository;

    public ThingViewModel() {
        repository = new ThingRepository();
    }

    public LiveData<List<Thing>> getThings() {
        return things;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void loadThings() {
        isLoading.setValue(true);
        repository.getThings(new ThingRepository.DataLoadListener() {
            @Override
            public void onDataLoaded(List<Thing> thingList) {
                isLoading.postValue(false);
                things.postValue(thingList);
            }

            @Override
            public void onDataLoadFailed() {
                isLoading.postValue(false);
                // Handle failure, e.g., by posting a value to an error LiveData
            }
        });
    }
}

