package com.example.webthingscontrollerapp.view.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webthingscontrollerapp.R;
import com.example.webthingscontrollerapp.view.viewmodel.model.pojo.Thing;

import java.util.List;

public class ThingAdapter extends RecyclerView.Adapter<ThingAdapter.ViewHolder> {

    private final List<Thing> thingList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView thingTitle, thingDescription;
        RecyclerView propertiesRecyclerView;

        public ViewHolder(View view) {
            super(view);
            thingTitle = view.findViewById(R.id.thingTitle);
            thingDescription = view.findViewById(R.id.thingDescription);
            propertiesRecyclerView = view.findViewById(R.id.propertiesRecyclerView);
        }
    }

    public ThingAdapter(List<Thing> thingList) {
        this.thingList = thingList;
    }

    @NonNull
    @Override
    public ThingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thing, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Thing thing = thingList.get(position);
        holder.thingTitle.setText(thing.getTitle());
        holder.thingDescription.setText(thing.getDescription());

        holder.propertiesRecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        PropertyAdapter propertyAdapter = new PropertyAdapter(thing.getProperties());
        holder.propertiesRecyclerView.setAdapter(propertyAdapter);

        holder.propertiesRecyclerView.setVisibility(thing.isExpanded() ? View.VISIBLE : View.GONE);

        holder.itemView.setOnClickListener(v -> {
            boolean isExpanded = thing.isExpanded();
            thing.setExpanded(!isExpanded);
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return thingList.size();
    }
}
