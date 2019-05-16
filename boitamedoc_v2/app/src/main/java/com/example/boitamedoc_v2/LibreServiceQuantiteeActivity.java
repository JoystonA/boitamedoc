package com.example.boitamedoc_v2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

//import android.widget.Toolbar;

public class LibreServiceQuantiteeActivity extends AppCompatActivity implements popup.popupListener {
//    private String username;
//    private String password;
    private LibreServiceQuantiteeFragment frag_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frag_create = new LibreServiceQuantiteeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,frag_create).commit();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(navListener);

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

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

            return true;
        }
    };

    @Override
    public void applyTexts(String Username, String Password) {
        //username=Username;
        //password=Password;
        frag_create.setUsername(Username);
        frag_create.setPassword(Password);
    }
}
