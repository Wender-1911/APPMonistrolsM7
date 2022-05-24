package com.wender.projectem07uf1nf2fa01danielmartinezwendersouzaoussamaelouardanipacoalvarado;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;

public class ArtistaAdapterFirestore
        extends FirestoreRecyclerAdapter<Artista,
        ArtistaAdapterFirestore.ArtistaHolder>
        implements View.OnClickListener {

    private String nom;
    private String cognom;
    private Bitmap imatge;

    private ArrayList<String> llistaNoms = new ArrayList<String>();
    private ArrayList<String> llistaCognoms = new ArrayList<String>();
    private ArrayList<Bitmap> llistaImatges = new ArrayList<Bitmap>();

    private View.OnClickListener listener;

    public ArrayList<String> getLlistaNoms() {
        return llistaNoms;
    }
    public ArrayList<String> getLlistaCognoms() {
        return llistaCognoms;
    }
    public ArrayList<Bitmap> getLlistaImatges() {
        return llistaImatges;
    }

    public ArtistaAdapterFirestore(@NonNull FirestoreRecyclerOptions<Artista> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ArtistaHolder holder, int position, @NonNull Artista model) {

        nom = model.getNom();
        cognom = model.getCognoms();

        llistaNoms.add(nom);
        llistaCognoms.add(cognom);


        holder.tvNomArtista.setText(nom);
        holder.tvCognomsArtista.setText(cognom);
        //holder.ivArtista.setImageResource(R.drawable.fotoartista1);

        imatge = BitmapFactory.decodeByteArray(model.getFoto().toBytes(), 0, model.getFoto().toBytes().length);

        llistaImatges.add(imatge);

        holder.ivArtista.setImageBitmap(
            Bitmap.createScaledBitmap(imatge, 400, 400, true)
        );
    }

    @NonNull
    @Override
    public ArtistaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artistes, parent, false);

        view.setOnClickListener(this);

        return new ArtistaHolder(view);
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if ( listener != null ) {
            listener.onClick(view);
        }
    }

    public class ArtistaHolder extends RecyclerView.ViewHolder {
        ImageView ivArtista;
        TextView tvNomArtista;
        TextView tvCognomsArtista;
        public ArtistaHolder(@NonNull View itemView) {
            super(itemView);

            ivArtista = itemView.findViewById(R.id.ivArtista);
            tvNomArtista = itemView.findViewById(R.id.tvNomArtista);
            tvCognomsArtista = itemView.findViewById(R.id.tvCognomsArtista);
        }
    }
}
