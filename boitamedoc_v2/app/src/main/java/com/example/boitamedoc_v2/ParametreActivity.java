package com.example.boitamedoc_v2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class ParametreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_parametre);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_param, new InfoCaseFragment()).commit();
        Button setting = findViewById(R.id.action_setting);
        //setting.setOnClickListener(navListener);
        //Toolbar toolbar = findViewById(R.id.app_bar);
        //setSupportActionBar(toolbar);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_accueil:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navigation_traitement:
                    selectedFragment = new TraitementFragment();
                    break;
                case R.id.navigation_boite:
                    selectedFragment = new boiteFragment();
                    break;
                case R.id.navigation_libre_service:
                    selectedFragment = new LibreServiceFragment();
                    break;
                case R.id.navigation_profil:
                    selectedFragment = new ProfilFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;

        }// Fin de onOptionItemSelected
    };
}