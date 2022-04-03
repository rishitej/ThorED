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
import com.example.thored.databinding.ActivitySqlcardBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SQLCardActivity extends AppCompatActivity {

    private ActivitySqlcardBinding binding;

    private String QUERRY, PROMPT, querryResponse1;

    private RequestQueue queue;

    public static final String BASE_URL = "http://137.116.32.204:5000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySqlcardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.q1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeVolleyRequest();
            }
        });

    }

    private void makeVolleyRequest() {
        QUERRY = binding.q1Et.getText().toString();
        PROMPT = "?prompt=";
        JSONObject param = new JSONObject();
        try {
            param.put("prompt", QUERRY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        queue = Volley.newRequestQueue(SQLCardActivity.this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                Uri.parse(BASE_URL+PROMPT+QUERRY).toString(), null,
                response -> {
                    try {
                        String code = response.getString("code");
                        binding.q1Tv.setVisibility(View.VISIBLE);
                        querryResponse1 = code;
                        binding.q1Tv.setText(querryResponse1);
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