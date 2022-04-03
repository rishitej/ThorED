package com.example.thored;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.example.thored.databinding.ActivityVisualCardBinding;

public class VisualCardActivity extends AppCompatActivity {

    private ActivityVisualCardBinding binding;

    private String QUERRY, PROMPT, querryResponse1;

    private RequestQueue queue;

    public static final String BASE_URL = "http://137.116.32.204:7001/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVisualCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}