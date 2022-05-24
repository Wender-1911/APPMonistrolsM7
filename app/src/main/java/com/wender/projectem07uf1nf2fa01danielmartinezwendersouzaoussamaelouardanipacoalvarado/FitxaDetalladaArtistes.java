package com.wender.projectem07uf1nf2fa01danielmartinezwendersouzaoussamaelouardanipacoalvarado;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;

public class FitxaDetalladaArtistes extends AppCompatActivity {

    String nom;
    String cognom;
    Bitmap imatge;

    TextView tvNomCognom;
    ImageView ivImatge;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitxa_detallada_artistes);

        tvNomCognom = (TextView) findViewById(R.id.tvNomArtista);
        ivImatge = (ImageView) findViewById(R.id.ivFotoArtista);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            String filename = extras.getString("imatge");

            imatge = new ImageSaver(getApplicationContext())
                    .setFileName(filename)
                    .setDirectoryName("images")
                    .load();

            nom = extras.getString("nom");
            cognom = extras.getString("cognom");

            ivImatge.setImageBitmap(imatge);
            tvNomCognom.setText(nom + " " + cognom);
        }
    }
}