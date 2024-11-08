package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText username;  // Declare as EditText
    private EditText password;  // Declare as EditText

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Apply window insets for system bar padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Find views
        username = findViewById(R.id.username); // Initialize EditText for username
        password = findViewById(R.id.password); // Initialize EditText for password
        Button loginButton = findViewById(R.id.loginButton);
        TextView signup = findViewById(R.id.SignupRedirect);

        // Set up login button listener
        loginButton.setOnClickListener(v -> {
            String email = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            // Input validation
            if (email.isEmpty()) {
                username.setError("Empty fields are not allowed");
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                username.setError("Please enter a valid email");
                return;
            }

            if (pass.isEmpty()) {
                password.setError("Empty fields are not allowed");
                return;
            }

            // Sign in with Firebase
            auth.signInWithEmailAndPassword(email, pass)
                    .addOnSuccessListener(authResult -> {
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(LoginActivity.this, "Login Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });

        // Set up signup redirect
        signup.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
