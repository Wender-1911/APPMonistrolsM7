package com.wender.projectem07uf1nf2fa01danielmartinezwendersouzaoussamaelouardanipacoalvarado;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FitxaDetalladaArtistes extends AppCompatActivity {

    String nom;
    String cognom;
    Bitmap imatge;

    TextView tvNomCognom;
    ImageView ivImatge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitxa_detallada_artistes);

        tvNomCognom = (TextView) findViewById(R.id.tvNomArtista);
        ivImatge = (ImageView) findViewById(R.id.ivFotoArtista);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            byte[] byteArray = extras.getByteArray("imatge");
            nom = extras.getString("nom");
            cognom = extras.getString("cognom");

            imatge = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

            ivImatge.setImageBitmap(imatge);
            tvNomCognom.setText(nom + " " + cognom);
        }

    }
}