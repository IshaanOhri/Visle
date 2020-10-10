package com.akshit.graphle;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface API {

    String BASE_URL = "https://visle.ml/channel/";

    @Headers("Content-Type: application/json")
    @POST("create")
    Call<CreateSessionResponse> createSession(@Body CreateSessionRequest createSessionRequest);

    @POST("recite")
    Call<ReciteSpeechResponse> reciteSpeech(@Body ReciteSpeechRequest reciteSpeechRequest);
}
