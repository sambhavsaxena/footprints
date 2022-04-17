package com.xtr.footprints;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        TextView signup = findViewById(R.id.textView3);
        signup.setOnClickListener(v -> {
            Intent intent = new Intent(this, register.class);
            startActivity(intent);
            finish();
        });

        //INCLUDING CONTEXTS

        EditText emailx = findViewById(R.id.editTextTextEmailAddress2);
        EditText pass = findViewById(R.id.loginpassword);
        Button button = findViewById(R.id.button4);
        FirebaseAuth authx = FirebaseAuth.getInstance();

        button.setOnClickListener(v -> {
            String email =  emailx.getText().toString().trim();
            String password =  pass.getText().toString().trim();

            if (TextUtils.isEmpty(email))
            {
                emailx.setError("Email field is empty");
                return;
            }
            if (TextUtils.isEmpty(password))
            {
                pass.setError("Password field is empty");
                return;
            }
            
            Toast.makeText(login.this, "Working on it", Toast.LENGTH_SHORT).show();

            //Authenticating the credentials

            authx.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful())
                {
                    SharedPreferences sharedPreferences = getSharedPreferences(startpage.PREFS_NAME, 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("hasloggedin", true);
                    editor.apply();
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(login.this, "Error " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
