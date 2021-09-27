package com.xtr.footprints;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

public class startpage extends AppCompatActivity {
    public static String PREFS_NAME = "MyPrefsFile";


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startpage);

        Button register = findViewById(R.id.button);
        register.setOnClickListener(v -> {
            Intent intent = new Intent(this, register.class);
            startActivity(intent);
            finish();
        });
        Button login = findViewById(R.id.button2);
        login.setOnClickListener(v -> {

            Intent intent = new Intent(this, login.class);
            startActivity(intent);
            finish();
        });
    }
}
