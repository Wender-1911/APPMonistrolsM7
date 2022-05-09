package com.wender.activitatbotonera;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.btnNav);
        //Amaga la barra d'adalt amb el nom de l'activitat
        getSupportActionBar().hide();


        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);

        //Amaga el menu al obrir el projecte
        bottomNavigationView.setVisibility(View.GONE);

        //Ens obra el Home al iniciar l'activitat
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

        //Espera 5 segons i llavors inicia el mètode obrirFundacio5Segons

        (new Handler()).postDelayed(this::obrirFundacio5Segons, 1000);
    }
    //Espera 5 segons i després inicia el Fragment Fundació
    private void obrirFundacio5Segons() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new FundacioFragment()).commit();
        //Mostra la botonera al acabar els 5 segons
        bottomNavigationView.setVisibility(View.VISIBLE);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;
        //Switch que detecta els layout al clicar en el botó i els cambia segons el escollit
        switch (item.getItemId())
        {
            //Per obrir Home en la següent entrega
            /*case R.id.vista_home:
                fragment = new HomeFragment();
                break;
                    <item android:id="@+id/vista_home" android:title="Home" android:icon="@drawable/ic_home"/>
             */

            case R.id.vista_fundacio:
                fragment = new FundacioFragment();
                break;

            case R.id.vista_mapa:
                fragment = new MapaFragment();
                break;

            case R.id.vista_escultures:
                fragment = new EsculturesFragment();
                break;

            case R.id.vista_artistes:
                fragment = new ArtistesFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            return true;
        }
    };
}