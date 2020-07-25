package com.example.gamelistsex.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.gamelistsex.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gamelistsex.R;
import com.example.gamelistsex.adapter.RecyclerViewAdapter;
import com.example.gamelistsex.model.Game;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static String URL = "https://rawg-video-games-database.p.rapidapi.com/developers";
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<Game> lstGame = new ArrayList<>();
    private RecyclerView myrv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ObtenerDatos();
        setRvadapter(lstGame);


    }

    private void setRvadapter(List<Game> lstGame) {
        myrv = (RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        myrv.setLayoutManager(layout);

        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,lstGame);
        myrv.setAdapter(myAdapter);

    }


    private void ObtenerDatos() {

        StringRequest request =  new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    //Convertir Response a un JSONObject cambiar estoy son dos linea la 1 y la 2
                    JSONArray mJsonArray = new JSONArray() ;   // response.getJSONArray("results");
                    for (int i = 0; i < response.length(); i++){
                        JSONObject mjsonObject = mJsonArray.getJSONObject(i);
                        Game game = new Game();
                        game.setName(mjsonObject.getString("name"));
                        game.setReleased(mjsonObject.getString("released"));
                        game.setRating(mjsonObject.getString("rating"));
                        game.setSlug(mjsonObject.getString("slug"));
                        game.setBackground_image(mjsonObject.getString("image_background"));

                        lstGame.add(game);



                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        }) {
            @Override
            public Map<String,String> getHeaders() {
                Map<String, String> customHeaders = new HashMap<>();
                customHeaders.put("x-rapidapi-host", "rawg-video-games-database.p.rapidapi.com");
                customHeaders.put("x-rapidapi-key", "f3e707eb5emsh1c1f2b509e98139p147a3djsnabd158f22092");
                return customHeaders;
            }
        };



        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);

    }

}