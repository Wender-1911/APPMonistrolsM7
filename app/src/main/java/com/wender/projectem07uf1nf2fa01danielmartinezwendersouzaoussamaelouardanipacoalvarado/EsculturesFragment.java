package com.wender.projectem07uf1nf2fa01danielmartinezwendersouzaoussamaelouardanipacoalvarado;

import android.content.Context;
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

public class EsculturesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Context context;

    RecyclerView rvEscultures;
    FirebaseFirestore db;
    EsculturaAdapterFirestore adapter;

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
        rvEscultures.setAdapter(adapter);

        /*Map<String, String> nom1 = new HashMap<String, String>();
        nom1.put("ca", "Sisif");
        nom1.put("es", "Sisif");
        nom1.put("eng", "Sisif");

        Double esculturalatitud1 = 41.756682;
        Double esculturalongitud1 = 2.021063;

        Double esculturalatitud2 = 41.757182;
        Double esculturalongitud2 = 2.020582;

        Double esculturalatitud3 = 41.757276;
        Double esculturalongitud3 = 2.01954;

        Double esculturalatitud4 = 41.7577;
        Double esculturalongitud4 = 2.019486;

        Double esculturalatitud5 = 41.757924;
        Double esculturalongitud5 = 2.014926;


        Map<String, String> nom2 = new HashMap<String, String>();
        nom2.put("ca", "La Família");
        nom2.put("es", "La Familia");
        nom2.put("eng", "The Family");

        Map<String, String> nom3 = new HashMap<String, String>();
        nom3.put("ca", "Monólit");
        nom3.put("es", "Monolito");
        nom3.put("eng", "Monolith");

        Map<String, String> nom4 = new HashMap<String, String>();
        nom4.put("ca", "Ballant amb les onades");
        nom4.put("es", "Bailando con las olas");
        nom4.put("eng", "Dancing with the waves");

        Map<String, String> nom5 = new HashMap<String, String>();
        nom5.put("ca", "Superfície esfèrica");
        nom5.put("es", "Superficie esférica");
        nom5.put("eng", "Spherical surface");

        List<Escultura> esc = Arrays.asList(
                new Escultura("1", nom1, null, null, null, null, null, null, null, esculturalatitud1, esculturalongitud1),
                new Escultura("2", nom2, null, null, null, null, null, null, null, esculturalatitud2, esculturalongitud2),
                new Escultura("3", nom3, null, null, null, null, null, null, null, esculturalatitud3, esculturalongitud3),
                new Escultura("4", nom4, null, null, null, null, null, null, null, esculturalatitud4, esculturalongitud4),
                new Escultura("5", nom5, null, null, null, null, null, null, null, esculturalatitud5, esculturalongitud5)
        );*/

        // Bucle que fa la inserció dels alumnes que conté la Collection alums.
        /*for (Escultura a: esc) {
            db.collection("escultures")
                    .document(a.getIdEscultura())
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