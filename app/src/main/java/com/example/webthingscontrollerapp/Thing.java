package com.example.webthingscontrollerapp;

import androidx.annotation.NonNull;

import java.util.Map;

public class Thing {
    private String title;
    private String description;
    private String href;
    private Map<String, Property> properties;

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

    public void setProperties(Map<String, Property> properties) {
        this.properties = properties;
    }

    @NonNull
    @Override
    public String toString() {
        return "Thing{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", href='" + href + '\'' +
                ", properties=" + properties +
                '}';
    }
}