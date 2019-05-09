package com.example.diskriminantas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        loadSettings();



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
            },SPLASH_TIME_OUT);
        }
    public void loadSettings()
    {
        SharedPreferences sharedPref= getSharedPreferences("Options", Context.MODE_PRIVATE);
        String name = sharedPref.getString("name","");
        TextView nameField = findViewById(R.id.textViewNameSPlash);
        if(name=="")
        {
            name = "guest";
        }
        nameField.setText("Good luck "+name);

    }
    }




