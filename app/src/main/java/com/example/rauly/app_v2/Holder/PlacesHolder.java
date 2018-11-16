package com.example.rauly.app_v2.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.rauly.app_v2.Models.Places;
import com.example.rauly.app_v2.R;


public class PlacesHolder extends RecyclerView.ViewHolder{

    private TextView placeName;
    private TextView avCost;
    private TextView opHour;
    private TextView clHour;

    public PlacesHolder(View itemView) {
        super(itemView);
        this.placeName = (TextView) itemView.findViewById(R.id.placeName);
        this.avCost = (TextView) itemView.findViewById(R.id.avCost);
        this.opHour = (TextView) itemView.findViewById(R.id.OpHour);
        this.clHour = (TextView) itemView.findViewById(R.id.ClHour);
    }
    public void updatePlace(Places place){
        placeName.setText(place.getName());
        avCost.setText("$" + place.getAvCost());
        opHour.setText(place.getOpenH());
        clHour.setText(place.getCloseH());
    }
}
