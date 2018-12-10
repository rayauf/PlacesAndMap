package com.example.rauly.app_v2.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rauly.app_v2.Activities.MainMenuAct;
import com.example.rauly.app_v2.Activities.PlacesDetail;
import com.example.rauly.app_v2.Adapter.PlacesAdapter;
import com.example.rauly.app_v2.Data.PlacesDataService;
import com.example.rauly.app_v2.Models.Places;
import com.example.rauly.app_v2.R;
import com.example.rauly.app_v2.Views.CardPadding;

import java.util.ArrayList;

public class PlacesDisplayFrag extends android.support.v4.app.Fragment {
    private PlacesAdapter adapter;
    private ArrayList<Places> places = new ArrayList<>();
    public static final String EXTRA_ITEM_PLACE = "PLACE";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View res = inflater.inflate(R.layout.activity_places_list,container,false);
        placesDL listener = new placesDL() {
            @Override
            public void success(Boolean success) {
                if (success) {
                    setUpRecycler(res);
                }
            }
        };
        setUpRecycler(res);
        places = PlacesDataService.getInstance().getAllPlaces(getContext(), listener);
        return res;
    }
    private void setUpRecycler(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_places);
        recyclerView.setHasFixedSize(true);
        adapter = new PlacesAdapter(places);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new CardPadding(0, 0, 0, 10));
    }
    public interface placesDL {
        void success(Boolean success);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
