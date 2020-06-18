package com.example.parcial3ercorte;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<Monumento>names;
    MetodosDB m;
    boolean isShow = false;


    public ArrayList<String> ArrayDeMon(Cursor cursor){
        cursor.moveToFirst();
        ArrayList<String> names = new ArrayList<String>();
        while(!cursor.isAfterLast()) {
            names.add(cursor.getString(cursor.getColumnIndex("nombre")));
            cursor.moveToNext();
        }
        cursor.close();
        return names;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        m= new MetodosDB(getApplicationContext());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        Cursor cursor = m.allMonumentos();
        agregarMarcadores(ArrayDeMon(cursor));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (!isShow) {
                    marker.showInfoWindow();
                    isShow = true;
                } else {
                    marker.hideInfoWindow();
                    isShow = false;
                }
                return true;
            }
        });


    }

    public void agregarMarcadores(ArrayList<String>monumentos){
        List<Address> addressList = new ArrayList<>();
        List<Address> lista = new ArrayList<>();
        Address a;


        for (String m:monumentos) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(m,1);
                a = addressList.get(0);
                lista.add(a);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
         int cont =0;

        for (Address list: lista) {

            try {
                Address address = list;
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title(monumentos.get(cont)).icon(BitmapDescriptorFactory.fromResource(R.drawable.iconx)));
                cont++;
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }



        }


}

        
