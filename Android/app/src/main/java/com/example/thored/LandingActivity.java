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
import android.view.View;

import com.example.thored.databinding.ActivityLandingBinding;
import com.google.firebase.auth.FirebaseAuth;

public class LandingActivity extends AppCompatActivity {

    private ActivityLandingBinding binding;

    private FirebaseAuth mAuth;

    private String username, welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        welcomeText = getResources().getString(R.string.welcome);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            username = bundle.getString("Username");

            welcomeText += " " + username + "!";
            binding.welcomeTv.setText(welcomeText);
        }else {
            welcomeText += " Hammer!";
            binding.welcomeTv.setText(welcomeText);
        }

        binding.sqlCard.setOnClickListener(view -> startActivity(new Intent(LandingActivity.this, SQLCardActivity.class)));
        binding.visualCard.setOnClickListener(view -> startActivity(new Intent(LandingActivity.this, VisualCardActivity.class)));
        binding.storyCard.setOnClickListener(view -> startActivity(new Intent(LandingActivity.this, StoryCardActivity.class)));
        binding.summaryCard.setOnClickListener(view -> startActivity(new Intent(LandingActivity.this, SummaryCardActivity.class)));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.setting) {
            startActivity(new Intent(Settings.ACTION_SETTINGS));
            return true;
        } else if (item.getItemId() == R.id.code) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/rishitej/ThorED")));
            return true;
        } else if (item.getItemId() == R.id.help) {
            startActivity(new Intent(Intent.ACTION_SENDTO, Uri
                    .fromParts("mailto","rishitejyp19c@iiitt.ac.in",null)));
            return true;
        } else if (item.getItemId() == R.id.logout) {
            mAuth.signOut();
            startActivity(new Intent(LandingActivity.this, MainActivity.class));
            finish();
            return true;
        } else if (item.getItemId() == R.id.exit) {
            System.exit(1);
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }
}