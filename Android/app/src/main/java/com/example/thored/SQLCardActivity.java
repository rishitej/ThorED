package com.example.thored;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.thored.databinding.ActivitySqlcardBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class SQLCardActivity extends AppCompatActivity {

    private ActivitySqlcardBinding binding;

    private String QUERRY;

    private RequestQueue queue;

    public static final String BASE_URL = "http://137.116.32.204:5000/?prompt=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySqlcardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

 //           makeVolleyRequest();

    }
//
//    private void makeVolleyRequest() {
//        QUERRY = binding.q1Et.getText().toString();
//        JSONObject param = new JSONObject();
//        try {
//            param.put("prompt", QUERRY);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        queue = Volley.newRequestQueue(SQLCardActivity.this);
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
//                Uri.parse(BASE_URL+QUERRY).toString(), null,
//                response -> {
//                    try {
//                        String code = response.getString("code");
//                        Toast.makeText(this, code, Toast.LENGTH_LONG).show();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }, error -> {
//            Toast.makeText(this, "ERROR: " + error.getMessage(), Toast.LENGTH_LONG).show();
//            Log.d("MainActivity LOG: ", "ERROR: " + error.getMessage());
//        });
//    }


}