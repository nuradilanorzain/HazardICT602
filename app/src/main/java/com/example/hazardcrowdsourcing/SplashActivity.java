package com.example.hazardcrowdsourcing;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;



public class SplashActivity extends AppCompatActivity {

    ImageView applicationName,splashimage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);



        applicationName = findViewById(R.id.appname);
        splashimage = findViewById(R.id.img);


        splashimage.animate().translationY(-2500).setDuration(1000).setStartDelay(4000);
        applicationName.animate().translationY(2000).setDuration(1000).setStartDelay(4000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashActivity.this, MainActivity.class));

            }
        },6000);

    }
}