package com.example.rauly.app_v2.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Places implements Parcelable{
    private String id = "";
    private String name = "";
    private Double latitude = 0.0;
    private Double longitude = 0.0;
    private Double avCost = 0.0;
    private String openH = "";
    private String closeH = "";
    private String category = "";


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getAvCost() {
        return avCost;
    }

    public String getOpenH() {
        return openH;
    }

    public String getCloseH() {
        return closeH;
    }

    public String getCategory() {
        return category;
    }

    public Places(String id, String name, Double latitude, Double longitude, Double avCost, String openH, String closeH, String category) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.avCost = avCost;
        this.openH = openH;
        this.closeH = closeH;
        this.category = category;
    }

        //
    private Places(Parcel in){
        id = in.readString();
        name = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        avCost = in.readDouble();
        openH = in.readString();
        closeH = in.readString();
        category = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeDouble(avCost);
        parcel.writeString(openH);
        parcel.writeString(closeH);
        parcel.writeString(category);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Places> CREATOR = new Parcelable.Creator<Places>(){
        public Places createFromParcel (Parcel in){
            return new Places(in);
        }
        public Places[] newArray(int size){
            return new Places[size];
        }
    };
}
