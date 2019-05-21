package com.example.boitamedoc_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

//import android.widget.Toolbar;

public class TraitementModificationActivity extends AppCompatActivity{
       private TraitementModificationFragment frag_create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frag_create = new TraitementModificationFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,frag_create).commit();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(navListener);
        setTitle("Traitement");


    }
    //Création d'une barre de tâche en bas de l'application avec la redirection vers chaque pages
    private BottomNavigationView.OnNavigationItemSelectedListener navListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_accueil:
                    selectedFragment = new HomeFragment();
                    setTitle("BOÎTA'MÉDOC");
                    break;
                case R.id.navigation_traitement:
                    selectedFragment = new TraitementFragment();
                    setTitle("Traitement");
                    break;
                case R.id.navigation_boite:
                    selectedFragment = new boiteFragment();
                    setTitle("Boîte de Médicament");
                    break;
                case R.id.navigation_libre_service:
                    selectedFragment = new LibreServiceFragment();
                    setTitle("Libre-Service");
                    break;
                case R.id.navigation_profil:
                    selectedFragment = new ProfilFragment();
                    setTitle("Profil");
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };//Fin de BottomNavigationView

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





