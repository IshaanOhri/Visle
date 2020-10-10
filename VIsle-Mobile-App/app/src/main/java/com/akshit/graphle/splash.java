package com.akshit.graphle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class splash extends AppCompatActivity {

    private ProgressDialog progressDialog;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final SharedPreferences sp = getSharedPreferences("GraphleSession", MODE_PRIVATE);
        final SharedPreferences.Editor et = sp.edit();

        final boolean sessionExist = sp.getBoolean("sessionExist", false);

        final Button startGraphling = findViewById(R.id.startGraphlingButton);

        startGraphling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!sessionExist){

                    final Dialog dialog = new Dialog(splash.this);
                    dialog.setContentView(R.layout.session_details_form);
                    dialog.show();

                    final TextView sessionName = dialog.findViewById(R.id.sessionName);
                    final TextInputLayout sessionNameLayout = dialog.findViewById(R.id.sessionNameLayout);
                    final TextView instructorName = dialog.findViewById(R.id.instructorName);
                    final TextInputLayout instructorNameLayout = dialog.findViewById(R.id.instructorNameLayout);

                    Button createSession = dialog.findViewById(R.id.createSessionButton);

                    createSession.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            hideKeyboard(splash.this);

                            String sessionNameVal = sessionName.getText().toString().trim();
                            String instructorNameVal = instructorName.getText().toString().trim();

                            if(sessionNameVal.isEmpty()){
                                sessionNameLayout.setError(null);
                                sessionNameLayout.setErrorEnabled(false);
                                sessionNameLayout.setError("Please enter session name");
                                sessionNameLayout.requestFocus();
                            }else if(instructorNameVal.isEmpty()){
                                instructorNameLayout.setError(null);
                                instructorNameLayout.setErrorEnabled(false);
                                instructorNameLayout.setError("Please enter your name.");
                                instructorNameLayout.requestFocus();
                            }else{
                                progressDialog = ProgressDialog.show(splash.this, "Session", "Creating new session...", false, false);

                                CreateSessionRequest createSessionRequest = new CreateSessionRequest(sessionNameVal, instructorNameVal);

                                API api = retrofit.create(API.class);
                                Call<CreateSessionResponse> call = api.createSession(createSessionRequest);

                                call.enqueue(new Callback<CreateSessionResponse>() {
                                    @Override
                                    public void onResponse(Call<CreateSessionResponse> call, Response<CreateSessionResponse> response) {
                                        Log.i("session response", "success");
                                        if (response.isSuccessful()) {
                                            if (response.body().isSuccess()) {
                                                ChannelInfo ci = response.body().getChannelInfo();
                                                String sessionUrl = "https://graphle.com/" + ci.getChannelID();
                                                Log.i("session url", sessionUrl);
                                                et.putBoolean("sessionExist", true);
                                                et.putString("sessionId", ci.getChannelID());
                                                et.putString("sessionUrl", sessionUrl);
                                                et.putString("sessionName", ci.getChannelName());
                                                et.putString("instructorName", ci.getInstructorName());
                                                et.commit();

                                                progressDialog.dismiss();

                                                Intent intent = new Intent(splash.this, speech.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);

                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<CreateSessionResponse> call, Throwable t) {
                                        progressDialog.dismiss();
                                        Log.i("session error", t.toString());
                                    }
                                });
                            }
                        }
                    });

                }else {
                    Intent intent = new Intent(splash.this, speech.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });

        new CountDownTimer(1500,1000)
        {

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                startGraphling.animate().translationYBy(-300).setDuration(500);
            }
        }.start();
    }

    public static void hideKeyboard( Activity activity ) {
        InputMethodManager imm = (InputMethodManager)activity.getSystemService( Context.INPUT_METHOD_SERVICE );
        View f = activity.getCurrentFocus();
        if( null != f && null != f.getWindowToken() && EditText.class.isAssignableFrom( f.getClass() ) )
            imm.hideSoftInputFromWindow( f.getWindowToken(), 0 );
        else
            activity.getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN );
    }
}
