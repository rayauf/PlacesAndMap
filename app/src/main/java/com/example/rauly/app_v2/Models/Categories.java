package com.example.rauly.app_v2.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Categories implements Parcelable{
    private String id = "";
    private String name = "";
    private String description = "";

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public Categories(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    private Categories (Parcel in){
        id = in.readString();
        name = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Categories> CREATOR = new Creator<Categories>() {
        @Override
        public Categories createFromParcel(Parcel in) {
            return new Categories(in);
        }

        @Override
        public Categories[] newArray(int size) {
            return new Categories[size];
        }
    };
}
