package com.example.gamelistsex.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.gamelistsex.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

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

    private static String URL = "https://rawg-video-games-database.p.rapidapi.com/games";
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private ArrayList<Game> lstGame = new ArrayList<>();
    private RecyclerView myrv;
    private TextView txt;
    private RecyclerViewAdapter myAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myrv = findViewById(R.id.rv);
        myrv.setHasFixedSize(true);
        myrv.setLayoutManager(new LinearLayoutManager(this));


        LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);



        ObtenerDatos();

    }


    private void ObtenerDatos() {

        requestQueue = Volley.newRequestQueue(MainActivity.this);

        StringRequest request =  new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    //Convertir Response a un JSONObject cambiar estoy son dos linea la 1 y la 2
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray mArray =  jsonObject.getJSONArray("results");
                    for (int i = 0; i < mArray.length(); i++){

                        JSONObject mjsonObject = mArray.getJSONObject(i);

                        String nombre = mjsonObject.getString("name");
                        String released = mjsonObject.getString("released");
                        String rating = mjsonObject.getString("rating");
                        String slug = mjsonObject.getString("slug");
                        String urlimagen = mjsonObject.getString("background_image");

                        lstGame.add(new Game(nombre, slug, released, rating, urlimagen));
                        //String name, String slug, String released, String rating, String background_image
                    }
                    myAdapter = new RecyclerViewAdapter(MainActivity.this, lstGame);
                    myrv.setAdapter(myAdapter);

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




        requestQueue.add(request);

    }

}
