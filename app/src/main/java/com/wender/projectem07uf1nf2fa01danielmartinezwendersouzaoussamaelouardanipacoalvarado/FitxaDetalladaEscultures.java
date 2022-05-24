package com.wender.projectem07uf1nf2fa01danielmartinezwendersouzaoussamaelouardanipacoalvarado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class FitxaDetalladaEscultures extends AppCompatActivity {

    String nom;
    Bitmap imatge;

    TextView tvNom;
    ImageView ivImatge;
    ImageButton ibReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitxa_detallada_escultures);

        ibReturn = (ImageButton) findViewById(R.id.returnEscultura);
        tvNom = (TextView) findViewById(R.id.tvNomEscultura);
        ivImatge = (ImageView) findViewById(R.id.ivFotoEscultura);

        ibReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return Button
            }
        });

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