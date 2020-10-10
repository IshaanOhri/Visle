package com.akshit.graphle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class speech extends AppCompatActivity {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    String sessionId;
    String sessionUrl;
    String sessionName;
    String instructorName;

    TextView speechText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);

        ImageButton shareIcon = findViewById(R.id.shareIcon);
        ImageButton deleteIcon = findViewById(R.id.deleteIcon);

        speechText = findViewById(R.id.speechText);

        ImageButton recordSpeech = findViewById(R.id.recordSpeechButton);
        TextView sessionIdValue = findViewById(R.id.sessionIdText);
        // TextView sessionUrlValue = findViewById(R.id.sessionUrlText);
        TextView sessionNameValue = findViewById(R.id.sessionNameText);
        TextView instructorNameValue = findViewById(R.id.instructorNameText);

        SharedPreferences sharedPreferences = getSharedPreferences("GraphleSession", MODE_PRIVATE);
        final SharedPreferences.Editor et = sharedPreferences.edit();

        sessionId = sharedPreferences.getString("sessionId", "test");
        sessionUrl = sharedPreferences.getString("sessionUrl", "test");
        sessionName = sharedPreferences.getString("sessionName", "test");
        instructorName = sharedPreferences.getString("instructorName", "test");

        sessionIdValue.setText(sessionId + " ");
        //sessionUrlValue.setText(sessionUrl + " ");
        sessionNameValue.setText(sessionName + " ");
        instructorNameValue.setText(instructorName + " ");

        shareIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");

                share.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share session url");
                share.putExtra(Intent.EXTRA_TEXT,"Join your graphle session at: " + sessionUrl);

                startActivity(share);
            }
        });

        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.putBoolean("sessionExist", false);
                et.commit();
                Intent intent = new Intent(speech.this, splash.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        recordSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak something");

                try{
                    startActivityForResult(intent,1);
                }catch(ActivityNotFoundException e){
                    Toast.makeText(speech.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if(resultCode == RESULT_OK && null != data){
                ArrayList<String> t = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                speechText.setText(t.get(0) + " ");
                // api call

                ReciteSpeechRequest reciteSpeechRequest = new ReciteSpeechRequest(sessionId, t.get(0));
                API api = retrofit.create(API.class);
                Call<ReciteSpeechResponse> call = api.reciteSpeech(reciteSpeechRequest);

                call.enqueue(new Callback<ReciteSpeechResponse>() {
                    @Override
                    public void onResponse(Call<ReciteSpeechResponse> call, Response<ReciteSpeechResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().isSuccess()) {
                                Toast.makeText(speech.this, "Done", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ReciteSpeechResponse> call, Throwable t) {
                        Toast.makeText(speech.this, "fail", Toast.LENGTH_SHORT).show();
                        Log.i("speech error", t.toString());
                    }
                });

            }
        }
    }
}
