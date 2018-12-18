package com.example.rauly.app_v2.Activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.rauly.app_v2.Fragments.MainViewFrag;
import com.example.rauly.app_v2.Fragments.PlacesDisplayFrag;
import com.example.rauly.app_v2.Interfaces.TextHandler;
import com.example.rauly.app_v2.R;

public class MainMenuAct extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, TextHandler{

    private DrawerLayout drawer;

    @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                    new MainViewFrag()).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                        new MainViewFrag()).commit();
                break;
            case R.id.nav_search:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                        new PlacesDisplayFrag()).addToBackStack(null).commit();
                break;

            default:
                return super.onOptionsItemSelected(item);

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void respond(String data) {
        PlacesDisplayFrag placesDisplayFrag = new PlacesDisplayFrag();
        Bundle bundle = new Bundle();
        bundle.putString("dat", data);
        placesDisplayFrag.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container, placesDisplayFrag).addToBackStack(null).commit();
    }
}
