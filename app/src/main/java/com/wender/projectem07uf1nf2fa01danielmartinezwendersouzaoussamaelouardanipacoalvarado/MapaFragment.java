package com.wender.projectem07uf1nf2fa01danielmartinezwendersouzaoussamaelouardanipacoalvarado;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MapaFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    FirebaseFirestore db;
    EsculturaAdapterFirestore adapter;

    private ArrayList<String> llistaNom = new ArrayList<String>();
    private ArrayList<Bitmap> llistaImatges = new ArrayList<Bitmap>();
    private ArrayList<String> llistaLatitud = new ArrayList<String>();
    private ArrayList<Bitmap> llistaLongitud = new ArrayList<Bitmap>();

    private String mParam1;
    private String mParam2;

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

                LatLng target = new LatLng(41.760524, 2.015460);
                MarkerOptions options = new MarkerOptions()
                    .position(target)
                    .title(Escultura.class.getName())
                    .snippet(Artista.class.getName());

                CustomInfoWindowsAdapter info = new CustomInfoWindowsAdapter(
                    MapaFragment.this.getActivity(),
                    "Titol",
                    "Artista",
                    (BitmapDrawable) getResources().getDrawable(R.drawable.foto1)
                );

                mMap.addMarker(options);

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(target, 13));
                mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);

                mMap.setInfoWindowAdapter(
                    info
                );
                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(@NonNull Marker marker) {

                    }
                });

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
    public void ClickEvent(View view) {
        // Fer un intent a FitxaDetallaArtistas pasan el nom, cognom, imatge, i descripcio
        /*
        int posicio = rvArtistes.getChildAdapterPosition(view);

        try {
            String filename = llistaNom.get(posicio) + "_" + llistaCognoms.get(posicio).replace(" ", "_") + ".png";

            //saveImage(context, llistaImatges.get(posicio), filename);
            new ImageSaver(getContext())
                    .setFileName(filename)
                    .setDirectoryName("images")
                    .save(llistaImatges.get(posicio));
            //Write file

            //Pop intent
            Intent intent = new Intent(ArtistesFragment.this.getActivity(), FitxaDetalladaArtistes.class);

            intent.putExtra("nom", llistaNom.get(posicio));
            intent.putExtra("cognom", llistaCognoms.get(posicio));
            intent.putExtra("imatge", filename);

            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }
}