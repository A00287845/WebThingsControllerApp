package com.example.webthingscontrollerapp.view.viewmodel.model.pojo;

import androidx.annotation.NonNull;

public class Property {
    private String name;
    private Object value;
    private String type;
    private boolean readOnly;
    private String title;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NonNull
    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", type='" + type + '\'' +
                ", readOnly=" + readOnly +
                ", title='" + title + '\'' +
                '}';
    }
}