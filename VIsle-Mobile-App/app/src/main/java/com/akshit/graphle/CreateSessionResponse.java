package com.akshit.graphle;

import com.google.gson.annotations.SerializedName;

public class CreateSessionResponse {
    @SerializedName("success")
    private boolean success;

    @SerializedName("channelInfo")
    private ChannelInfo channelInfo;

    public boolean isSuccess() {
        return success;
    }

    public ChannelInfo getChannelInfo() {
        return channelInfo;
    }
}
