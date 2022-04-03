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
import com.example.thored.databinding.ActivitySummaryCardBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SummaryCardActivity extends AppCompatActivity {

    private ActivitySummaryCardBinding binding;

    private String QUERRY4, PROMPT, querryResponse4;

    private RequestQueue queue;

    public static final String BASE_URL4 = "http://137.116.32.204:7000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySummaryCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.q4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeVolleyRequest();
            }
        });
    }

    private void makeVolleyRequest() {
        QUERRY4 = binding.q4Et.getText().toString();
        PROMPT = "?prompt=";
        JSONObject param = new JSONObject();
        try {
            param.put("prompt", QUERRY4);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        queue = Volley.newRequestQueue(SummaryCardActivity.this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                Uri.parse(BASE_URL4+PROMPT+QUERRY4).toString(), null,
                response -> {
                    try {
                        String code = response.getString("code");
                        binding.q4Tv.setVisibility(View.VISIBLE);
                        querryResponse4 = code;
                        binding.q4Tv.setText(code);
                        Toast.makeText(this, "request successful!", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
            Toast.makeText(this, "ERROR: a " + error.getMessage(), Toast.LENGTH_LONG).show();
            Log.d("MainActivity LOG: ", "ERROR: b " + error.getMessage());
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