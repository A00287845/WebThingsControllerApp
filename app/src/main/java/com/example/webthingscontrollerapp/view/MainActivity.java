package com.example.webthingscontrollerapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.webthingscontrollerapp.R;
import com.example.webthingscontrollerapp.view.util.ThingAdapter;
import com.example.webthingscontrollerapp.view.viewmodel.model.pojo.Property;
import com.example.webthingscontrollerapp.view.viewmodel.model.pojo.Thing;
import com.example.webthingscontrollerapp.view.viewmodel.ThingViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static String LOG_STRING = "A00287845_";
    private ThingViewModel viewModel;
    private boolean initialised;

    private ThingAdapter thingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_STRING, "MainActivity onCreate()");
        super.onCreate(savedInstanceState);
        initialised = false;
        setContentView(R.layout.activity_main);


        viewModel = new ViewModelProvider(this).get(ThingViewModel.class);

        viewModel.getIsLoading().observe(this, isLoading -> {
            // TODO loading indicator
        });

        viewModel.getAlteredProperty().observe(this, property -> {
            if(initialised){
                thingAdapter.updateProperty(property);
            }
        });

        viewModel.getThings().observe(this, things -> {
            Log.d(LOG_STRING, "Observed success");

            for (Thing t : things) {
                Log.d(LOG_STRING, t.toString());
            }
            manageRecyclerView(things);
        });
        viewModel.loadThings();
    }

    private void manageRecyclerView(List<Thing> things) {
        if (!initialised) {
            RecyclerView thingRecyclerView = findViewById(R.id.thingRecyclerView);
            thingAdapter = new ThingAdapter(things, (newValue, propertyToUpdate) -> viewModel.updateThingProperty(newValue, propertyToUpdate));
            thingRecyclerView.setAdapter(thingAdapter);
            initialised = true;
        } else {
            thingAdapter.updateData(things);
        }
    }

    public interface PropertyAdapterInteractionListener {
        void onItemClicked(String newValue, Property propertyToUpdate);
    }
}