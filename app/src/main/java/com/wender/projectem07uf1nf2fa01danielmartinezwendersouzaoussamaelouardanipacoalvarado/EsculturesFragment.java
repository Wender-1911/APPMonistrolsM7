package com.wender.projectem07uf1nf2fa01danielmartinezwendersouzaoussamaelouardanipacoalvarado;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class EsculturesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Context context;

    RecyclerView rvEscultures;

    FirebaseFirestore db;
    EsculturaAdapterFirestore adapter;

    private ArrayList<String> llistaNom = new ArrayList<String>();
    private ArrayList<Bitmap> llistaImatges = new ArrayList<Bitmap>();

    public EsculturesFragment() {

    }

    public static EsculturesFragment newInstance(String param1, String param2) {
        EsculturesFragment fragment = new EsculturesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_escultures, container, false);
        db = FirebaseFirestore.getInstance();
        rvEscultures = (RecyclerView)view.findViewById(R.id.rvEscultures);
        rvEscultures.setHasFixedSize(false);
        rvEscultures.setLayoutManager(new GridLayoutManager(context, 2));

        Query consulta = db.collection("Escultures").limit(50);

        FirestoreRecyclerOptions<Escultura> opcions =
            new FirestoreRecyclerOptions
                .Builder<Escultura>()
                .setQuery(consulta, Escultura.class)
                .build();

        adapter = new EsculturaAdapterFirestore(opcions);

        llistaNom = adapter.getLlistaNoms();
        llistaImatges = adapter.getLlistaImatges();

        rvEscultures.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickEvent(view);
            }
        });

        return view;
    }
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    // Si l'activity queda oculta o finalitza, aturem el mode "Listening" de l'Adapter ja
    // que consumeix recursos de forma innecessària si resta activat mentre l'activity no
    // no es veu.
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public void ClickEvent(View view) {
        // Fer un intent a FitxaDetallaArtistas pasan el nom, cognom, imatge, i descripcio
        int posicio = rvEscultures.getChildAdapterPosition(view);

        try {
            String filename = llistaNom.get(posicio).replace(" ", "_") + ".png";

            new ImageSaver(getContext())
                    .setFileName(filename)
                    .setDirectoryName("images")
                    .save(llistaImatges.get(posicio));

            //Pop intent
            Intent intent = new Intent(EsculturesFragment.this.getActivity(), FitxaDetalladaEscultures.class);

            intent.putExtra("nom", llistaNom.get(posicio));
            intent.putExtra("imatge", filename);

            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}