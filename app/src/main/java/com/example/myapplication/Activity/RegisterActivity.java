//package com.example.myapplication.Activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import com.example.myapplication.R;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.Firebase;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//
//public class RegisterActivity extends AppCompatActivity {
//    private FirebaseAuth auth;
//    EditText username;
//    EditText password;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_register);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        TextView loginredirect = findViewById(R.id.loginredirect);
//        auth = FirebaseAuth.getInstance();
//            String usernameText = username.getText().toString();
//            String passwordText = password.getText().toString();
//
//        loginredirect.setOnClickListener(v -> {
//            if(!usernameText.isEmpty()){
//                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
////                return;
//            }
//            if(!passwordText.isEmpty()){
//                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
////                return;
//            }else {
//                auth.createUserWithEmailAndPassword(usernameText, passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(RegisterActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(RegisterActivity.this, "SignUp Failed", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//            }
//
//                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                });
//
//
//    }
//}


package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Initialize EditText views
        username = findViewById(R.id.email); // Ensure these IDs match those in activity_register.xml
        password = findViewById(R.id.password);

        // Apply system bar insets padding to root view
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Register button functionality
        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(v -> {
            String usernameText = username.getText().toString().trim();
            String passwordText = password.getText().toString().trim();

            // Input validation
            if (usernameText.isEmpty()) {
                Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
                return;
            }
            if (passwordText.isEmpty()) {
                Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create user with Firebase
            auth.createUserWithEmailAndPassword(usernameText, passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "SignUp Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });

        // Redirect to login
        TextView loginredirect = findViewById(R.id.loginredirect);
        loginredirect.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}

