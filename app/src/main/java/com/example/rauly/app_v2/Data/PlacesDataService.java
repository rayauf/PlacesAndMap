package com.example.rauly.app_v2.Data;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.rauly.app_v2.Activities.PlacesListView;
import com.example.rauly.app_v2.Constants.Constants;
import com.example.rauly.app_v2.Models.Places;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlacesDataService {
    private static PlacesDataService instance = new PlacesDataService();

    public static PlacesDataService getInstance() {
        return instance;
    }

    private PlacesDataService() {
    }
    //Request all places---------------------------------------------------------------------------------------------------------------------------
    public ArrayList<Places> getAllPlaces(Context context, final PlacesListView.placesDL listener){
        String url = Constants.GET_ALL_PLACES;

        final ArrayList<Places> placesList = new ArrayList<>();
        final JsonArrayRequest getPlaces = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response.toString());
                try {
                    JSONArray places = response;
                    for (int i = 0; i < places.length(); i++) {
                        JSONObject place = places.getJSONObject(i);
                        String placeName = place.getString("name");
                        String id = place.getString("_id");
                        Double avCost = place.getDouble("avgCost");

                        JSONObject horary = place.getJSONObject("horary");
                        JSONObject details = horary.getJSONObject("details");
                        String openH = details.getString("open");
                        String closeH = details.getString("close");

                        JSONObject coordinates = place.getJSONObject("coordinates");
                        JSONObject det = coordinates.getJSONObject("details");
                        Double latitude = det.getDouble("latitude");
                        Double longitude = det.getDouble("longitude");

                        Places newPlace = new Places(id, placeName, latitude, longitude, avCost, openH, closeH);
                        placesList.add(newPlace);

                    }
                } catch (JSONException e) {
                    Log.v("JSON", "EXC" + e.getLocalizedMessage());
                }
                listener.success(true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("api", "Error-------------------------------------------------------------------------------" + error.getLocalizedMessage());
            }
        });
        Volley.newRequestQueue(context).add(getPlaces);
        return placesList;
    }
}
