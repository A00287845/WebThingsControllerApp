package com.example.webthingscontrollerapp.view.viewmodel.model.pojo;

import androidx.annotation.NonNull;

public class Link {
    private String rel;
    private String href;
    private String mediaType;

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @NonNull
    @Override
    public String toString() {
        return "Link{" +
                "rel='" + rel + '\'' +
                ", href='" + href + '\'' +
                ", mediaType='" + mediaType + '\'' +
                '}';
    }
}