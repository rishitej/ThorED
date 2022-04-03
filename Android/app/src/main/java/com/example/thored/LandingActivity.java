package com.example.thored;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import com.example.thored.databinding.ActivityLandingBinding;
import com.google.firebase.auth.FirebaseAuth;

public class LandingActivity extends AppCompatActivity {

    private ActivityLandingBinding binding;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.profile) {
            startActivity(new Intent(Settings.ACTION_SETTINGS));
            return true;
        } else if (item.getItemId() == R.id.code) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/rishitej/")));
            return true;
        } else if (item.getItemId() == R.id.help) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com")));
            return true;
        } else if (item.getItemId() == R.id.logout) {
            mAuth.signOut();
            startActivity(new Intent(LandingActivity.this, MainActivity.class));
            return true;
        } else if (item.getItemId() == R.id.exit) {
            System.exit(1);
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }
}