package com.project.themovies.model;

import java.io.Serializable;

/**
 * Created by Achmad Qomarudin on 14-10-2020.
 */

public class ModelTrailer implements Serializable {

    private String key;
    private String type;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
