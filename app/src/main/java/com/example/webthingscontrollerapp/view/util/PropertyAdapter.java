package com.example.webthingscontrollerapp.view.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webthingscontrollerapp.R;
import com.example.webthingscontrollerapp.view.viewmodel.model.pojo.Property;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.ViewHolder> {

    private final List<Property> propertyList = new ArrayList<>(); // Store properties as a list internally

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView propertyName, propertyValue, propertyType;
        public EditText editPropertyValue;
        public Button submitEdit;

        public ViewHolder(View view) {
            super(view);
            propertyName = view.findViewById(R.id.propertyName);
            propertyValue = view.findViewById(R.id.propertyValue);
            propertyType = view.findViewById(R.id.propertyType);
            editPropertyValue = view.findViewById(R.id.editPropertyValue);
            submitEdit = view.findViewById(R.id.submitEdit);
        }
    }

    public PropertyAdapter(Map<String, Property> properties) {
        this.propertyList.addAll(properties.values());
    }

    @NonNull
    @Override
    public PropertyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_property, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Property property = propertyList.get(position);
        holder.propertyName.setText(property.getName());
        holder.propertyValue.setText(property.getValue().toString());
        holder.propertyType.setText(property.getType());

        if (!property.isReadOnly()) {
            holder.editPropertyValue.setVisibility(View.VISIBLE);
            holder.submitEdit.setVisibility(View.VISIBLE);
        } else {
            holder.editPropertyValue.setVisibility(View.GONE);
            holder.submitEdit.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return propertyList.size();
    }
}

