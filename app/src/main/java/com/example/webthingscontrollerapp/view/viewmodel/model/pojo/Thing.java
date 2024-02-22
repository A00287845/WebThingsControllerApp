package com.example.webthingscontrollerapp.view.viewmodel.model.pojo;

import static com.example.webthingscontrollerapp.view.MainActivity.LOG_STRING;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Thing {
    private String title;
    private String description;
    private String href;
    private Map<String, Property> properties;

    private Map<String, Object> propertyValues;
    private List<Link> links;
    private boolean isExpanded;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Map<String, Property> getProperties() {
        return properties;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public Map<String, Object> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(Map<String, Object> propertyValues) {
        Log.d(LOG_STRING, "setPropertyValues()");
        this.propertyValues = propertyValues;

        for (Map.Entry<String, Object> entry : propertyValues.entrySet()) {

            String key = entry.getKey();
            Log.d(LOG_STRING, "setPropertyValues() checking key " + key);
            Object valueFromProperties = entry.getValue();

            if (properties.containsKey(key)) {
                Objects.requireNonNull(properties.get(key)).setValue(valueFromProperties);
            }
        }
    }

    public void setProperties(Map<String, Property> properties) {
        this.properties = properties;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public String getPropertiesUrl(){
        for(Link l: links){
            if(l.getRel().equals("properties")){
                return l.getHref();
            }
        }
        return "invalid or no links found";
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    @NonNull
    @Override
    public String toString() {
        return "Thing{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", href='" + href + '\'' +
                ", properties=" + properties +
                ", links=" + links +
                ", isExpanded=" + isExpanded +
                '}';
    }
}