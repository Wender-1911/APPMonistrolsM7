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

public class EsculturaAdapterFirestore
        extends FirestoreRecyclerAdapter<Escultura, EsculturaAdapterFirestore.EsculturaHolder>
        implements View.OnClickListener {

    private String nom;
    private Bitmap imatge;

    private ArrayList<String> llistaNoms = new ArrayList<String>();
    private ArrayList<Bitmap> llistaImatges = new ArrayList<Bitmap>();

    private View.OnClickListener listener;

    public ArrayList<String> getLlistaNoms() {
        return llistaNoms;
    }
    public ArrayList<Bitmap> getLlistaImatges() {
        return llistaImatges;
    }

    public EsculturaAdapterFirestore(@NonNull FirestoreRecyclerOptions<Escultura> options) {
        super(options);
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
    @Override
    protected void onBindViewHolder(@NonNull EsculturaHolder holder, int position, @NonNull Escultura model) {

        nom = model.getNom().get("ca");

        llistaNoms.add(nom);

        holder.tvNomEscultura.setText(nom);

        //holder.ivEscultura.setImageResource(R.drawable.foto2);
        imatge = BitmapFactory.decodeByteArray(model.getImatges().get(0).toBytes(), 0, model.getImatges().get(0).toBytes().length);

        llistaImatges.add(imatge);

        holder.ivEscultura.setImageBitmap(
            Bitmap.createScaledBitmap(
                    imatge, 400, 400, true
            )
        );
    }

    @NonNull
    @Override
    public EsculturaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.escultures, parent, false);

        view.setOnClickListener(this);

        return new EsculturaHolder(view);
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