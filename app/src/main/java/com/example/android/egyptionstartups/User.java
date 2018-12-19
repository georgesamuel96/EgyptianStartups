package com.example.android.egyptionstartups;

import com.google.gson.annotations.SerializedName;

public class User {
@SerializedName("response")
    private String response ;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("name")
    private String name;

    public User() {
    }

}
