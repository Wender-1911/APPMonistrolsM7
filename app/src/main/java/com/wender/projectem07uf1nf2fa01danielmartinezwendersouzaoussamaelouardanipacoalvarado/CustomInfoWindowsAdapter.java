package com.wender.projectem07uf1nf2fa01danielmartinezwendersouzaoussamaelouardanipacoalvarado;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowsAdapter
        implements GoogleMap.InfoWindowAdapter,
        View.OnClickListener
{
    private View.OnClickListener listener;

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
        rendowWindowText(marker, mWindwow);
        return mWindwow;
    }

    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        rendowWindowText(marker, mWindwow);
        return mWindwow;
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
}
