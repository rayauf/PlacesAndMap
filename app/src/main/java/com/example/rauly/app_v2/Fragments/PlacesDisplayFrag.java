package com.example.rauly.app_v2.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rauly.app_v2.Activities.MainMenuAct;
import com.example.rauly.app_v2.Activities.PlacesDetail;
import com.example.rauly.app_v2.Adapter.PlacesAdapter;
import com.example.rauly.app_v2.Data.CategoriesDataService;
import com.example.rauly.app_v2.Data.PlacesDataService;
import com.example.rauly.app_v2.Models.Categories;
import com.example.rauly.app_v2.Models.Places;
import com.example.rauly.app_v2.R;
import com.example.rauly.app_v2.Views.CardPadding;

import java.util.ArrayList;

public class PlacesDisplayFrag extends android.support.v4.app.Fragment {
    private PlacesAdapter adapter;
    private ArrayList<Places> places = new ArrayList<>();
    private EditText editText;
    private Button searchButton;
    private String hint;
    public static final String EXTRA_ITEM_PLACE = "PLACE";

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle = getArguments();
        System.out.println("On Start -----------------------------------");
        if(bundle != null){                                                 //Genera la busqueda por categorias si bundle es diferente de null
            editText = (EditText) getView().findViewById(R.id.editSearch);
            editText.setText(bundle.getString("dat"));
            hint = bundle.getString("dat");
            placesDL listener = new placesDL() {
                @Override
                public void success(Boolean success) {
                    if (success) {
                        setUpRecycler(getView());
                    }
                }
            };
            setUpRecycler(getView());
            places = PlacesDataService.getInstance().getPlacesbyCat(getContext(), listener, bundle.getString("cat"));

        }
        else{
            placesDL listener = new placesDL() {
                @Override
                public void success(Boolean success) {
                    if (success) {
                        setUpRecycler(getView());
                    }
                }
            };
            setUpRecycler(getView());
            places = PlacesDataService.getInstance().getAllPlaces(getContext(), listener);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        searchButton = (Button) getView().findViewById(R.id.SearchButton);
        editText = (EditText) getView().findViewById(R.id.editSearch);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hint = editText.getText().toString();
                System.out.println(hint + "////////////////////////////////////////////////////////////////////////");
                placesDL listener = new placesDL() {
                    @Override
                    public void success(Boolean success) {
                        if(success){
                            setUpRecycler(getView());
                        }
                    }
                };
                setUpRecycler(getView());
                places = PlacesDataService.getInstance().getPlacesByHint(getContext(),listener,hint);
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        System.out.println("On create view ------------------------------");
        final View res = inflater.inflate(R.layout.activity_places_list,container,false);
        return res;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        System.out.println("OnView Created ----------------------------------");

        super.onViewCreated(view, savedInstanceState);
    }

    private void setUpRecycler(View view) {                                                         //Gral Search
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
