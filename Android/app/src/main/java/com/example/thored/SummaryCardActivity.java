package com.example.thored;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.example.thored.databinding.ActivitySummaryCardBinding;

public class SummaryCardActivity extends AppCompatActivity {

    private ActivitySummaryCardBinding binding;

    private String QUERRY, PROMPT, querryResponse1;

    private RequestQueue queue;

    public static final String BASE_URL = "http://137.116.32.204:7000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySummaryCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}