package com.example.rauly.app_v2.Data;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.rauly.app_v2.Constants.Constants;
import com.example.rauly.app_v2.Models.Categories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesDataService {
    private static CategoriesDataService instance = new CategoriesDataService();

    public static CategoriesDataService getInstance() {
        return instance;
    }

    private CategoriesDataService (){

    }
    public ArrayList<Categories> getAllCats(Context context){
        String url = "";

        final ArrayList<Categories> categoriesList = new ArrayList<>();
        final JsonArrayRequest getCats = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response.toString());
                try {
                    JSONArray categories = response;
                    for (int i = 0; i < categories.length(); i++) {
                        JSONObject category = categories.getJSONObject(i);
                        String id = category.getString("_id");
                        String name = category.getString("name");
                        String description = category.getString("description");

                        Categories newCat = new Categories(id, name, description);
                        categoriesList.add(newCat);
                    }
                } catch (JSONException e) {
                    Log.v("JSON", "EXC", e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("api", "Error-------------------------------------------------------------------------------" + error.getLocalizedMessage());
            }
        });
        Volley.newRequestQueue(context).add(getCats);
        return categoriesList;
    }
}
