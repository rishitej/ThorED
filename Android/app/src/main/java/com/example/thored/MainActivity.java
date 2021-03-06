package com.example.thored;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.thored.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private FirebaseAuth mAuth;

    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.emailEt.getText().toString();
                String password = binding.passwordEt.getText().toString();

                if(email.isEmpty()){
                    binding.emailEt.setError("Enter Email!");
                    binding.emailEt.requestFocus();
                    return;
                }

                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    binding.emailEt.setError("Enter valid email!");
                    binding.emailEt.requestFocus();
                    return;
                }

                else if(password.isEmpty()){
                    binding.passwordEt.setError("Enter Password!");
                    binding.passwordEt.requestFocus();
                    return;
                }

                else{
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, LandingActivity.class));
                                finish();
                            }else{
                                Toast.makeText(MainActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        binding.forgotPTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ")));
            }
        });

        binding.signupTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if(user != null){
            startActivity(new Intent(MainActivity.this, LandingActivity.class));
            finish();
        }
    }
}