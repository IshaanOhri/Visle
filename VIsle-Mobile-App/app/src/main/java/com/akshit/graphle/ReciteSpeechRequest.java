package com.akshit.graphle;

public class ReciteSpeechRequest {

    private String channelID;
    private String query;

    public ReciteSpeechRequest(String channelID, String query) {
        this.channelID = channelID;
        this.query = query;
    }
}
