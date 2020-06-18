package com.example.parcial3ercorte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
   //AIzaSyCLlA2ws0BZuNYWy65jXU8GmJfJP82qGho
    RadioButton rdArtificial, rdNatural;
    Button RegistrarX,MapaX;
    EditText nombreSitio, pais, ciudad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        RegistrarX=findViewById(R.id.btnRegistrar);
        RegistrarX.setOnClickListener(this);

        MapaX=findViewById(R.id.btnMapa);
        MapaX.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnRegistrar:
                Intent i1 = new Intent(getApplicationContext(),RegistrarAct.class);
                startActivity(i1);
                break;
            case R.id.btnMapa:
                Intent i2 = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(i2);
                break;

        }
        }
}
