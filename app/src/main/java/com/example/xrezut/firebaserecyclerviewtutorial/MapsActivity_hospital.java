package com.example.xrezut.firebaserecyclerviewtutorial;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MapsActivity_hospital extends FragmentActivity implements OnMapReadyCallback,LocationListener,GoogleMap.OnMarkerClickListener {


    private GoogleMap mMap;
    private DatabaseReference mDatabase;


        Marker marker;
    ChildEventListener mChildEventListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_hospital);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Cities").child("Ambala");


        }


    @Override
    public void onMapReady(GoogleMap googleMap){

        mMap = googleMap;
        googleMap.setOnMarkerClickListener(this);

        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        addMarkersToMap();



    }

    @Override
    public void onStop(){
        if(mChildEventListener != null)
            mDatabase.removeEventListener(mChildEventListener);
        super.onStop();
    }

    private void addMarkersToMap(){

        mChildEventListener = mDatabase.addChildEventListener(new ChildEventListener() {

            public void onChildAdded(DataSnapshot dataSnapshot,String s) {
                News marker_news = dataSnapshot.getValue(News.class);
               Double latitude = marker_news.getLatitude();
              Double longitude = marker_news.getLongitude();
                LatLng location = new LatLng(latitude,longitude);
                mMap.addMarker(new MarkerOptions().position(location).title(dataSnapshot.getKey()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
                mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );
            }



            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}


