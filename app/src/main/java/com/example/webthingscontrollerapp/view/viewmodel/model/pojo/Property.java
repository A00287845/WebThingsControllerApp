package com.example.webthingscontrollerapp.view.viewmodel.model.pojo;

import androidx.annotation.NonNull;

import java.util.List;

public class Property {
    private String name;
    private Object value;
    private String type;
    private boolean readOnly;
    private String title;
    private List<Link> links;
    private String owner;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

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

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public String getPropertyUrl(){
        for(Link l: links){
            if(l.getRel().equals("property")){
                return l.getHref();
            }
        }
        return "invalid or no links found";
    }

    @NonNull
    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", type='" + type + '\'' +
                ", readOnly=" + readOnly +
                ", thisPropertiesUrl=" + getPropertyUrl() +
                ", title='" + title + '\'' +
                '}';
    }
}