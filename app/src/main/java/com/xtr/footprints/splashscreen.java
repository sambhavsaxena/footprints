package com.xtr.footprints;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class splashscreen extends AppCompatActivity {
    Animation logo;
    ImageView image;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(splashscreen.this, R.color.green));
        getWindow().setFlags(WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW, WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);
        setContentView(R.layout.splashscreenactivity);
        logo = AnimationUtils.loadAnimation(this, R.anim.central_animation);
        image = findViewById(R.id.imageView2);
        image.setAnimation(logo);
        int SPLASH_SCREEN = 1000;
        new Handler().postDelayed(() -> {
            SharedPreferences sharedPreferences = getSharedPreferences(startpage.PREFS_NAME, 0);
            boolean hasloggedin = sharedPreferences.getBoolean("hasloggedin", false);
            if (hasloggedin)
            {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Intent intent = new Intent(this, startpage.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}
