package com.wender.activitatbotonera;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtistesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private Context context;
    RecyclerView rvArtistes;
    FirebaseFirestore db;
    ArtistaAdapterFirestore adapter;
    private FirebaseStorage storage;
    public ArtistesFragment() {
        // Required empty public constructor
    }

    public static ArtistesFragment newInstance(String param1, String param2) {
        ArtistesFragment fragment = new ArtistesFragment();
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
        View view = inflater.inflate(R.layout.fragment_artistes, container, false);
        db = FirebaseFirestore.getInstance();
        rvArtistes = (RecyclerView)view.findViewById(R.id.rvArtistes);
        rvArtistes.setHasFixedSize(false);
        rvArtistes.setLayoutManager(new LinearLayoutManager(context));
        Query consulta = db.collection("Artistes").limit(50);
        FirestoreRecyclerOptions<Artista> opcions =
                new FirestoreRecyclerOptions
                        .Builder<Artista>()
                        .setQuery(consulta, Artista.class)
                        .build();

        adapter = new ArtistaAdapterFirestore(opcions);
        rvArtistes.setAdapter(adapter);

        /*Map<String, String> biografia = new HashMap<String, String>();
        String bioCésar = "Nascut a el 1928 a Vegadeo (Astúries)\n" +
                "Va estudiar a l’Escuela Superior de Bellas Artes de Madrid.\n" +
                "Posteriorment, va residir a Itàlia, des\n" +
                "d’on, en constant formació, va fer viatges d’estudis per aquell país, Grècia,\n" +
                "França, Bèlgica, Holanda, Alemanya, Àustria i Anglaterra.\n" +
                "Va guanyar nombrosos premis i guardons i ha fet exposicions, individuals i\n" +
                "col·lectives arreu del món.\n" +
                "A partir del 1960 passà a viure a Madrid, on instal·là el seu taller, i on morí\n" +
                "l’any 2000.";
        biografia.put("ca", bioCésar);

        Map<String, String> correntArtistic = new HashMap<String, String>();
        String cArtisticCésar = "És un escultor d’arrels classicitzants, però que amb el pas del temps ha\n" +
                "anat evolucionant, a través de l’experimentació en materials, formats\n" +
                "i volums, cap a l’abstracció, desdibuixant les formes fins a fer-les de\n" +
                "vegades difícils de reconèixer-les.\n" +
                "Ha treballat principalment amb la pedra i el metall, sobretot bronze.";
        correntArtistic.put("ca", bioCésar);

        List<Artista> arts = Arrays.asList(
                new Artista("1", "César", "Montaña García", 1928, 2000, biografia, correntArtistic, null, null),
                new Artista("2", "Sebastià", "Badia Cerdà", 1916, 2009, null, null, null, null),
                new Artista("3", "Charles Henry", "Collet Colomb", 1902, 1983, null, null, null, null),
                new Artista("4", "Joan", "Serafini Masdeu", 1931, 2017, null, null, null, null),
                new Artista("5", "Vicente", "Larrea Gayarre", 1934, 2000, null, null, null, null)
        );

        // Bucle que fa la inserció dels alumnes que conté la Collection alums.
        for (Artista a: arts) {
            db.collection("artistes")
                    .document(a.getIdArtista())
                    .set(a)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void v) {
                            // En cas que la inserció hagi anat bé, no farem res en especial.
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                }
                                            }
            );
        }*/
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
}