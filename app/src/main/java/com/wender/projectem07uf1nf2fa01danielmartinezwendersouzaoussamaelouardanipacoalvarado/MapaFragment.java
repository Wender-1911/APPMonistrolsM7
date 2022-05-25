package com.wender.projectem07uf1nf2fa01danielmartinezwendersouzaoussamaelouardanipacoalvarado;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MapaFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ArrayList<String> llistaNom = new ArrayList<String>();
    private ArrayList<Bitmap> llistaImatges = new ArrayList<Bitmap>();
    private ArrayList<Double> llistaLatitud = new ArrayList<>();
    private ArrayList<Double> llistaLongitud = new ArrayList<>();

    private String mParam1;
    private String mParam2;

    int pos = 0;

    public MapaFragment() {
        // Required empty public constructor
    }

    public static MapaFragment newInstance(String param1, String param2) {
        MapaFragment fragment = new MapaFragment();
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

        View rootView = inflater.inflate(R.layout.fragment_mapa, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.frg);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.clear(); //clear old markers

                BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.mapaicone);
                Bitmap b = bitmapdraw.getBitmap();

                Bitmap smallMarker = Bitmap.createScaledBitmap(b, 44, 64, false);

                LatLng target = new LatLng(41.760524, 2.015460);

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                ArrayList<CustomInfoWindowsAdapter> info = new ArrayList<CustomInfoWindowsAdapter>();

                db.collection("Escultures")
                    //.document()
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot doc : task.getResult()) {
                                    Escultura esc = doc.toObject(Escultura.class);

                                    llistaNom.add(esc.getNom().get("ca"));

                                    Bitmap imatge = BitmapFactory.decodeByteArray(esc.getImatges().get(0).toBytes(), 0, esc.getImatges().get(0).toBytes().length);
                                    llistaImatges.add(imatge);

                                    mMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(esc.getLatitud(), esc.getLongitud()))
                                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                                    );

                                    info.add(new CustomInfoWindowsAdapter(
                                        MapaFragment.this.getActivity(),
                                        esc.getNom().get("ca"),
                                        esc.getArtista(),
                                        imatge
                                    ));

                                    mMap.setInfoWindowAdapter(info.get(pos));
                                }
                            }
                        }
                    });
                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(@NonNull Marker marker) {
                        String id = marker.getId();
                        id = id.substring(1);
                        int idNumber = Integer.parseInt(id);
                        System.out.println(idNumber);
                        ClickEvent(idNumber);
                    }
                });

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(target, 13));
                mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        marker.showInfoWindow();
                        return true;
                    }
                });
            }
        });
        return rootView;
    }
    public void ClickEvent(int posicio) {
        // Fer un intent a FitxaDetallaEscultura pasan el nom, cognom, imatge, i descripcio

        try {
            String filename = llistaNom.get(posicio).replace(" ", "_") + ".png";

            new ImageSaver(getContext())
                    .setFileName(filename)
                    .setDirectoryName("images")
                    .save(llistaImatges.get(posicio));

            //Pop intent
            Intent intent = new Intent(MapaFragment.this.getActivity(), FitxaDetalladaEscultures.class);

            intent.putExtra("nom", llistaNom.get(posicio));
            intent.putExtra("imatge", filename);

            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}