package com.example.webthingscontrollerapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.webthingscontrollerapp.R;
import com.example.webthingscontrollerapp.view.util.ThingAdapter;
import com.example.webthingscontrollerapp.view.viewmodel.model.pojo.Thing;
import com.example.webthingscontrollerapp.view.viewmodel.ThingViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static String LOG_STRING = "A00287845_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_STRING, "MainActivity onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ThingViewModel viewModel = new ViewModelProvider(this).get(ThingViewModel.class);

        viewModel.getIsLoading().observe(this, isLoading -> {
            // TODO loading indicator
        });

        viewModel.getThings().observe(this, things -> {
            Log.d(LOG_STRING, "Observed success");

            for(Thing t : things){
                Log.d(LOG_STRING, t.toString());
            }
            setupRecyclerView(things);
        });
        viewModel.loadThings();
    }

    private void setupRecyclerView(List<Thing> things){
        RecyclerView thingRecyclerView = findViewById(R.id.thingRecyclerView);
        ThingAdapter thingAdapter = new ThingAdapter(things);
        thingRecyclerView.setAdapter(thingAdapter);
    }
}