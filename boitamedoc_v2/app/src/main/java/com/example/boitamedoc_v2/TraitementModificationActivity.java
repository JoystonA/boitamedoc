package com.example.boitamedoc_v2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

//import android.widget.Toolbar;

public class TraitementModificationActivity extends AppCompatActivity {
    private TextInputLayout nbre_medoc_modification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new TraitementModificationFragment()).commit();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(navListener);
        setTitle("Trait45ement");
        nbre_medoc_modification= findViewById(R.id.nbr_medicament);

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
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }// Fin de onOptionItemSelected


    private boolean validateNbrMedicatment() {
        String nbrMedocInput = nbre_medoc_modification.getEditText().getText().toString().trim();
        if(nbrMedocInput.isEmpty()){
            nbre_medoc_modification.setError("Le champs est vide");
            return false;
        }
        else{
            nbre_medoc_modification.setError(null);
            nbre_medoc_modification.setErrorEnabled(false);
            return true;
        }
    }


    public void confirmInput(View v) {
        if(!validateNbrMedicatment()) {
            return;
        }
        String input= "Nombre de médicament :" +nbre_medoc_modification.getEditText().getText().toString();
        input +="\n";
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }
}





