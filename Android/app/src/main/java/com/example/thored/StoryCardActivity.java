package com.example.thored;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.thored.databinding.ActivityStoryCardBinding;
import com.example.thored.databinding.ActivitySummaryCardBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StoryCardActivity extends AppCompatActivity {

    private ActivityStoryCardBinding binding;

    private String QUERRY2, PROMPT, querryResponse2;

    private RequestQueue queue;

    public static final String BASE_URL = "http://137.116.32.204:9000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStoryCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.q2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeVolleyRequest();
            }
        });
    }

    private void makeVolleyRequest() {
        QUERRY2 = binding.q2Et.getText().toString();
        PROMPT = "?prompt=";
        JSONObject param = new JSONObject();
        try {
            param.put("prompt", QUERRY2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        queue = Volley.newRequestQueue(StoryCardActivity.this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                Uri.parse(BASE_URL+PROMPT+QUERRY2).toString(), null,
                response -> {
                    try {
                        String code = response.getString("code");
                        binding.q2Tv.setVisibility(View.VISIBLE);
                        querryResponse2 = code;
                        binding.q2Tv.setText(querryResponse2);
                        Toast.makeText(this, "request successful!", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
            Toast.makeText(this, "ERROR: " + error.getMessage(), Toast.LENGTH_LONG).show();
            Log.d("MainActivity LOG: ", "ERROR: " + error.getMessage());
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        queue.add(request);
    }
}