package com.example.thored;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Patterns;
import android.view.View;

import com.example.thored.databinding.ActivityMainBinding;
import com.example.thored.databinding.ActivityRegisterBinding;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = binding.emailREt.getText().toString().trim();
                String username = binding.usernameREt.getText().toString().trim();
                String password = binding.passwordREt.getText().toString().trim();
                String confirmpassword = binding.confirmpasswordREt.getText().toString().trim();

                if(email.isEmpty()){
                    binding.emailREt.setError("Enter Email!");
                    binding.emailREt.requestFocus();
                    return;
                }

                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    binding.emailREt.setError("Enter valid email!");
                    binding.emailREt.requestFocus();
                    return;
                }

                else if(username.isEmpty()){
                    binding.usernameREt.setError("Enter Username!");
                    binding.usernameREt.requestFocus();
                    return;
                }

                else if(password.isEmpty()){
                    binding.passwordREt.setError("Enter Password!");
                    binding.passwordREt.requestFocus();
                    return;
                }

                else if(confirmpassword.isEmpty()){
                    binding.confirmpasswordREt.setError("Confirm Password!");
                    binding.confirmpasswordREt.requestFocus();
                    return;
                }

                else if(!confirmpassword.equals(password)){
                    binding.confirmpasswordREt.setError("Password doesn't Match!");
                    binding.confirmpasswordREt.requestFocus();
                    return;
                }


            }
        });
    }
}