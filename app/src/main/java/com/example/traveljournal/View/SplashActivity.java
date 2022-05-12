package com.example.traveljournal.View;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.example.traveljournal.R;

import java.util.Objects;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed((Runnable) () -> {
            Intent intent = new Intent(SplashActivity.this, DrawerActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_TIME_OUT);
    }
}