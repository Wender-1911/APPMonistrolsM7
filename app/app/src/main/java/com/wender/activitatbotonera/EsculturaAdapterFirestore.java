package com.wender.activitatbotonera;

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

public class EsculturaAdapterFirestore extends FirestoreRecyclerAdapter<Escultura, EsculturaAdapterFirestore.EsculturaHolder> {

    public EsculturaAdapterFirestore(@NonNull FirestoreRecyclerOptions<Escultura> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull EsculturaAdapterFirestore.EsculturaHolder holder, int position, @NonNull Escultura model) {
        holder.tvNomEscultura.setText(model.getNom().get("ca"));
        //holder.ivEscultura.setImageResource(R.drawable.foto2);
        Bitmap bmp = BitmapFactory.decodeByteArray(model.getImatges().get(0).toBytes(), 0, model.getImatges().get(0).toBytes().length);
        holder.ivEscultura.setImageBitmap(
                Bitmap.createScaledBitmap(
                        bmp, 400, 400, true));
    }

    @NonNull
    @Override
    public EsculturaAdapterFirestore.EsculturaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.escultures, parent, false);
        return new EsculturaAdapterFirestore.EsculturaHolder(view);
    }

    public class EsculturaHolder extends RecyclerView.ViewHolder {
        ImageView ivEscultura;
        TextView tvNomEscultura;

        public EsculturaHolder(@NonNull View itemView) {
            super(itemView);

            ivEscultura = itemView.findViewById(R.id.ivEscultura);
            tvNomEscultura = itemView.findViewById(R.id.tvNomEscultura);
        }
    }
}