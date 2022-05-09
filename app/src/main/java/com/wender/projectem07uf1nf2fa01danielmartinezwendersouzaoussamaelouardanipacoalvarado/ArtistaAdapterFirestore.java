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

public class ArtistaAdapterFirestore extends FirestoreRecyclerAdapter<Artista, ArtistaAdapterFirestore.ArtistaHolder>{

    public ArtistaAdapterFirestore(@NonNull FirestoreRecyclerOptions<Artista> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ArtistaHolder holder, int position, @NonNull Artista model) {
        holder.tvNomArtista.setText(model.getNom());
        holder.tvCognomsArtista.setText(model.getCognoms());
        //holder.ivArtista.setImageResource(R.drawable.fotoartista1);
        Bitmap bmp = BitmapFactory.decodeByteArray(model.getFoto().toBytes(), 0, model.getFoto().toBytes().length);
        holder.ivArtista.setImageBitmap(
                Bitmap.createScaledBitmap(
                        bmp, 400, 400, true));
    }

    @NonNull
    @Override
    public ArtistaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artistes, parent, false);
        return new ArtistaHolder(view);
    }

    public class ArtistaHolder extends RecyclerView.ViewHolder {
        ImageView ivArtista;
        TextView tvNomArtista, tvCognomsArtista;
        public ArtistaHolder(@NonNull View itemView) {
            super(itemView);

            ivArtista = itemView.findViewById(R.id.ivArtista);
            tvNomArtista = itemView.findViewById(R.id.tvNomArtista);
            tvCognomsArtista = itemView.findViewById(R.id.tvCognomsArtista);
        }
    }
}
