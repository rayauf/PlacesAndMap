package com.example.rauly.app_v2.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rauly.app_v2.Activities.PlacesDetail;
import com.example.rauly.app_v2.Fragments.PlacesDisplayFrag;
import com.example.rauly.app_v2.Holder.PlacesHolder;
import com.example.rauly.app_v2.Models.Places;
import com.example.rauly.app_v2.R;

import java.util.ArrayList;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesHolder>{

    private ArrayList<Places> places;
    public  PlacesAdapter (ArrayList<Places> places){this.places = places;}
    @Override
    public void onBindViewHolder(PlacesHolder holder, int position) {
        final Places place = places.get(position);
        holder.updatePlace(place);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacesDisplayFrag placesDisplayFrag = new PlacesDisplayFrag();
                Intent intent = new Intent(v.getContext(), PlacesDetail.class);
                intent.putExtra(placesDisplayFrag.EXTRA_ITEM_PLACE,place);
                v.getContext().startActivity(intent);

            }
        });
    }
    @Override
    public int getItemCount() {
        return places.size();
    }
    @Override
    public PlacesHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View placeCard = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_places, parent, false);
        return new PlacesHolder(placeCard);
    }




}
