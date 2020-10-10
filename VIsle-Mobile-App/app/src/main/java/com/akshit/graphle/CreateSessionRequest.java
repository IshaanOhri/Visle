package com.akshit.graphle;

public class CreateSessionRequest {

    private String channelName;
    private String instructorName;

    public CreateSessionRequest(String channelName, String instructorName) {
        this.channelName = channelName;
        this.instructorName = instructorName;
    }
}
