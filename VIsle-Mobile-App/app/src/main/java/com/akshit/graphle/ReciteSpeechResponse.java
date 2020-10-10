package com.akshit.graphle;

import com.google.gson.annotations.SerializedName;

public class ReciteSpeechResponse {

    @SerializedName("success")
    private boolean success;

    public boolean isSuccess() {
        return success;
    }
}
