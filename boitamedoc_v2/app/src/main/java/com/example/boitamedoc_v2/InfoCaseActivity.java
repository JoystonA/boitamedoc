package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

//import android.widget.Toolbar;

public class InfoCaseActivity extends AppCompatActivity implements popup.popupListener{
    private String username;
    private String password;

    @Override
    public void applyTexts(String Username, String Password) {
        username = Username;
        password = Password;
        Log.d("théo", "applyTexts/ Username :" +username +" Password : " + password );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new InfoCaseFragment()).commit();
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

    //Création d'un menu sur la bar d'action en haut de l'application
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }//Fin de OnCreateOptionMenu

    //Redirection vers la page de setting
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_setting :
                openSetting();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openSetting() {
        Intent intent;
        intent = new Intent(this, ParametreActivity.class);
        startActivity(intent);
    }

}
