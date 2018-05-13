package com.example.xrezut.firebaserecyclerviewtutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;

public class MainActivity_maps extends AppCompatActivity {
 private DatabaseReference mref;
 Button get_direction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hospital_maps);

get_direction = findViewById(R.id.button2);

get_direction.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

            // TODO Auto-generated method stub
            Intent i = new Intent(getApplicationContext(),MapsActivity_hospital.class);
            startActivity(i);
            setContentView(R.layout.activity_maps_hospital);
        }

});

    }





}
