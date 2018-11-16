package com.example.rauly.app_v2.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.rauly.app_v2.Adapter.PlacesAdapter;
import com.example.rauly.app_v2.Data.PlacesDataService;
import com.example.rauly.app_v2.Models.Places;
import com.example.rauly.app_v2.R;
import com.example.rauly.app_v2.Views.CardPadding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlacesListView extends AppCompatActivity {
    private PlacesAdapter adapter;
    private ArrayList<Places> places = new ArrayList<>();
    private static PlacesListView placesListView;
    public static final String EXTRA_ITEM_PLACE = "PLACE";

    public static PlacesListView getPlacesListView() {
        return placesListView;
    }

    public static void setPlacesListView(PlacesListView placesListView) {
        PlacesListView.placesListView = placesListView;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_list);
        placesListView.setPlacesListView(this);
        placesDL listener = new placesDL() {
            @Override
            public void success(Boolean success) {
                if (success){
                    setUpRecycler();
                }
            }
        };
        setUpRecycler();
        places = PlacesDataService.getInstance().getAllPlaces(this, listener);

    }
    private void setUpRecycler(){
        RecyclerView recyclerView = findViewById(R.id.recycler_places);
        recyclerView.setHasFixedSize(true);
        adapter = new PlacesAdapter(places);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new CardPadding(0,0,0,10));
    }
    public interface placesDL{
        void success(Boolean success);
    }
    public void loadPlacesDetail(Places place){
        Intent intent = new Intent(PlacesListView.this, PlacesDetail.class);
        intent.putExtra(PlacesListView.EXTRA_ITEM_PLACE, place);
        startActivity(intent);
    }
}
