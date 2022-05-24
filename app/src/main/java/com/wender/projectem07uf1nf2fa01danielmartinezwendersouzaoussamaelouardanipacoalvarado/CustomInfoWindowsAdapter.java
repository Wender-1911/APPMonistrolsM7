package com.wender.projectem07uf1nf2fa01danielmartinezwendersouzaoussamaelouardanipacoalvarado;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
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

public class CustomInfoWindowsAdapter implements GoogleMap.InfoWindowAdapter
{
    private final View mWindwow;
    private Context mContext;
    private String title;
    private String artista;
    private BitmapDrawable image;

    public CustomInfoWindowsAdapter(Context context, String title, String artista, BitmapDrawable image) {
        mContext = context;
        this.title = title;
        this.artista = artista;
        this.image = image;
        mWindwow = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null);
    }

    private void rendowWindowText(Marker marker, View view) {
        Bitmap selectedImage = image.getBitmap();
        ImageView picture = (ImageView) view.findViewById(R.id.image);

        picture.setImageBitmap(selectedImage);

        String title = this.title;
        TextView tvTitle = (TextView) view.findViewById(R.id.title);

        if (!title.equals("")) {
            tvTitle.setText(title);
        }

        String snippet = this.artista;
        TextView tvSnipper = (TextView) view.findViewById(R.id.author);

        if(!snippet.equals("")) {
            tvSnipper.setText(snippet);
        }

        TextView link = (TextView) view.findViewById(R.id.link);
        link.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public View getInfoContents(@NonNull Marker marker) {
        //Bucle per pillar tota la info i guardarla per arrays
        /*Pots copiar-ho desde aqui
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ArrayList<String> llistaNom = new ArrayList<String>();
        ArrayList<Bitmap> llistaImatges = new ArrayList<Bitmap>();
        ArrayList<Double> llistaLatitud = new ArrayList<>();
        ArrayList<Double> llistaLongitud = new ArrayList<>();

        db.collection("Escultures")
                //.document()
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                           @Override
                                           public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                               if (task.isSuccessful()) {
                                                   for(QueryDocumentSnapshot doc: task.getResult()) {
                                                       Escultura esc = doc.toObject(Escultura.class);
                                                               mMap.addMarker(new MarkerOptions()
                                                                       .position(new LatLng(esc.getLatitud(), esc.getLongitud()))
                                                                       .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                                                       llistaNom.add(esc.getNom().get("ca"));
                                                       llistaLatitud.add(esc.getLatitud());
                                                       llistaLongitud.add(esc.getLongitud());

                                                       for (int i = 0; i < llistaNom.size(); i++) {
                                                           Log.d("NOMBUCLE", llistaNom.get(i));
                                                           Log.d("llistaLongitud", ""+ llistaLongitud.get(i));
                                                           Log.d("llistaLongitud", ""+ llistaLatitud.get(i));
                                                       }
                                                   }
                                               }
                                           }
                                       }

                );
        Fins aqui*/
        /*for (int i = 0; i < llistaNom.size(); i++) {
            title = llistaNom.get(i);
            ((TextView)mWindwow.findViewById(R.id.title)).setText(llistaNom.get(i));
            ((TextView)mWindwow.findViewById(R.id.author)).setText("Author");
        }*/
        rendowWindowText(marker, mWindwow);
        return mWindwow;
    }

    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        rendowWindowText(marker, mWindwow);
        return mWindwow;
    }
}
