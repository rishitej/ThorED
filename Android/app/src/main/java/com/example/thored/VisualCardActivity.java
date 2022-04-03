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
import com.example.thored.databinding.ActivityVisualCardBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VisualCardActivity extends AppCompatActivity {

    private ActivityVisualCardBinding binding;

    private String QUERRY3, PROMPT, querryResponse3;

    private RequestQueue queue;

    public static final String BASE_URL3 = "http://137.116.32.204:7001/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVisualCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.q3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeVolleyRequest();
            }
        });
    }
    private void makeVolleyRequest() {
        QUERRY3 = binding.q3Et.getText().toString();
        PROMPT = "?prompt=";
        JSONObject param = new JSONObject();
        try {
            param.put("prompt", QUERRY3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        queue = Volley.newRequestQueue(VisualCardActivity.this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                Uri.parse(BASE_URL3+PROMPT+QUERRY3).toString(), null,
                response -> {
                    try {
                        String code = response.getString("code");
                        String image = response.getString("image");
                        binding.q3Tv.setVisibility(View.VISIBLE);
                        binding.q32Tv.setVisibility(View.VISIBLE);
                        querryResponse3 = code;
                        binding.q3Tv.setText(code);
                        binding.q32Tv.setText(image);
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