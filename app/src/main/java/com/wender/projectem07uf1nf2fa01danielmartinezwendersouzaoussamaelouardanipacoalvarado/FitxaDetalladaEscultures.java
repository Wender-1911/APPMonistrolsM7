package com.wender.projectem07uf1nf2fa01danielmartinezwendersouzaoussamaelouardanipacoalvarado;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FitxaDetalladaEscultures extends AppCompatActivity {

    String nom;
    Bitmap imatge;

    TextView tvNom;
    ImageView ivImatge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitxa_detallada_escultures);

        tvNom = (TextView) findViewById(R.id.tvNomEscultura);
        ivImatge = (ImageView) findViewById(R.id.ivFotoEscultura);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String filename = extras.getString("imatge");

            imatge = new ImageSaver(getApplicationContext())
                    .setFileName(filename)
                    .setDirectoryName("images")
                    .load();

            nom = extras.getString("nom");

            ivImatge.setImageBitmap(imatge);
            tvNom.setText(nom);
        }
    }
}