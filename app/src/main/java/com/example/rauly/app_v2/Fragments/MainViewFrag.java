package com.example.rauly.app_v2.Fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rauly.app_v2.Interfaces.TextHandler;
import com.example.rauly.app_v2.R;

import static com.example.rauly.app_v2.Constants.Categs.CAFETERIA;
import static com.example.rauly.app_v2.Constants.Categs.ENTRETAIMENT;
import static com.example.rauly.app_v2.Constants.Categs.INTERNATIONAL;

public class MainViewFrag extends android.support.v4.app.Fragment{

    private TextView textHandler;
    TextHandler comm;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        comm = (TextHandler) getActivity();
        CardView op1 = (CardView) getActivity().findViewById(R.id.Op1);
        CardView op2 = (CardView) getActivity().findViewById(R.id.Op2);
        CardView op3 = (CardView) getActivity().findViewById(R.id.Op3);
        CardView op4 = (CardView) getActivity().findViewById(R.id.Op4);
        CardView op5 = (CardView) getActivity().findViewById(R.id.Op5);
        CardView op6 = (CardView) getActivity().findViewById(R.id.Op6);

        op1.setOnClickListener(new CardView.OnClickListener() {
            @Override
            public void onClick(View v) {
                textHandler = v.findViewById(R.id.cat1_string);
                comm.respond(textHandler.getText().toString(), CAFETERIA);
            }
        });

        op2.setOnClickListener(new CardView.OnClickListener() {
            @Override
            public void onClick(View v) {
                textHandler = v.findViewById(R.id.cat2_string);
                comm.respond(textHandler.getText().toString(), INTERNATIONAL);
            }
        });
        op3.setOnClickListener(new CardView.OnClickListener() {
            @Override
            public void onClick(View v) {
                textHandler = v.findViewById(R.id.cat3_string);
                comm.respond(textHandler.getText().toString(), ENTRETAIMENT);
            }
        });
        op4.setOnClickListener(new CardView.OnClickListener() {
            @Override
            public void onClick(View v) {
                textHandler = v.findViewById(R.id.cat4_string);
                comm.respond(textHandler.getText().toString(), "");
            }
        });
        op5.setOnClickListener(new CardView.OnClickListener() {
            @Override
            public void onClick(View v) {
                textHandler = v.findViewById(R.id.cat5_string);
                comm.respond(textHandler.getText().toString(), "");
            }
        });
        op6.setOnClickListener(new CardView.OnClickListener() {
            @Override
            public void onClick(View v) {
                textHandler = v.findViewById(R.id.cat6_string);
                comm.respond(textHandler.getText().toString(), "");
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.main_view_layout,container,false);
        return v;
    }
}
