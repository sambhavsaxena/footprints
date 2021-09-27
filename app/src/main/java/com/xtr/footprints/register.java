package com.xtr.footprints;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class register extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeractivity);
        getWindow().setStatusBarColor(ContextCompat.getColor(register.this, R.color.green));
        TextView login = findViewById(R.id.textView2);
        login.setOnClickListener(v -> {
            Intent intent = new Intent(this, login.class);
            startActivity(intent);
            finish();
        });
        EditText emailx = findViewById(R.id.editTextTextEmailAddress);
        EditText pass1 = findViewById(R.id.registerpassword1);
        EditText pass2 = findViewById(R.id.registerpassword2);
        Button button = findViewById(R.id.button3);
        FirebaseAuth authx = FirebaseAuth.getInstance();

        //VALIDATING THE DATA ENTERED BY THE USER
        button.setOnClickListener(v -> {
            String email = emailx.getText().toString().trim();
            String password = pass1.getText().toString().trim();
            String rpassword = pass2.getText().toString().trim();

            if (TextUtils.isEmpty(email))
            {
                emailx.setError("Email field is empty");
                return;
            }
            if (TextUtils.isEmpty(password))
            {
                pass1.setError("Password field is empty");
                return;
            }
            if (TextUtils.isEmpty(rpassword))
            {
                pass2.setError("Password field is empty");
                return;
            }
            if (!password.equals(rpassword))
            {
                pass2.setError("Passwords do not match");
                return;
            }
            if (password.length() < 8)
            {
                pass1.setError("Password should be more than 8 characters");
                return;
            }

            //REGISTER USER IN FIREBASE

            authx.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(register.this, "Account Created", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), login.class));
                        finish();
                    }
                    else
                    {
                        Toast.makeText(register.this, "Error" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }
}
