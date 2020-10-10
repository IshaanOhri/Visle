package com.akshit.graphle;

import com.google.gson.annotations.SerializedName;

public class ChannelInfo {
    @SerializedName("channelID")
    private String channelID;

    @SerializedName("channelName")
    private String channelName;

    @SerializedName("instructorName")
    private String instructorName;

    public String getChannelID() {
        return channelID;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getInstructorName() {
        return instructorName;
    }
}
