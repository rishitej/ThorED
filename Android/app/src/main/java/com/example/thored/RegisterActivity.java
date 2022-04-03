package com.example.thored;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.thored.databinding.ActivityMainBinding;
import com.example.thored.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    private FirebaseAuth mAuth;

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

                if (email.isEmpty()) {
                    binding.emailREt.setError("Enter Email!");
                    binding.emailREt.requestFocus();
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.emailREt.setError("Enter valid email!");
                    binding.emailREt.requestFocus();
                    return;
                } else if (username.isEmpty()) {
                    binding.usernameREt.setError("Enter Username!");
                    binding.usernameREt.requestFocus();
                    return;
                } else if (password.isEmpty()) {
                    binding.passwordREt.setError("Enter Password!");
                    binding.passwordREt.requestFocus();
                    return;
                } else if (confirmpassword.isEmpty()) {
                    binding.confirmpasswordREt.setError("Confirm Password!");
                    binding.confirmpasswordREt.requestFocus();
                    return;
                } else if (!confirmpassword.equals(password)) {
                    binding.confirmpasswordREt.setError("Password doesn't Match!");
                    binding.confirmpasswordREt.requestFocus();
                    return;
                } else {

                    Bundle bundle = new Bundle();
                    bundle.putString("Username", username);

                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
//                                        User user = new User(email, username);
//
//                                        FirebaseDatabase.getInstance().getReference("Users")
//                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                            @Override
//                                            public void onComplete(@NonNull Task<Void> task) {
//
//                                                if (task.isSuccessful()) {
                                                    Toast.makeText(RegisterActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(RegisterActivity.this,LandingActivity.class).putExtras(bundle));
                                                    finish();
//                                                } else {
//                                                    Toast.makeText(RegisterActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                                }
//                                            }
//                                        });

                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}