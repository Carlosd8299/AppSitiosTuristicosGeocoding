package com.example.parcial3ercorte;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.nio.MappedByteBuffer;
import java.util.ArrayList;

public class RegistrarAct extends AppCompatActivity implements View.OnClickListener {

    RadioButton rdArtificialM, rdNaturalM;
    Button Registrar;
    EditText nombreSitio, pais, ciudad, descripcion;

    MetodosDB metodos;
    Monumento mon;

    ArrayList<Monumento>monumentos = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        nombreSitio=findViewById(R.id.txtNombreSitio);
        pais=findViewById(R.id.txtPais);
        ciudad=findViewById(R.id.txtCuidad);
        descripcion=findViewById(R.id.txtDescripcion);
        rdArtificialM=findViewById(R.id.rdArtificial);
        rdNaturalM=findViewById(R.id.rdNatural);

        Registrar= findViewById(R.id.btnGuardar);
        Registrar.setOnClickListener(this);

        metodos = new MetodosDB(getApplicationContext());

        ////Intent i3=new Intent(this, MapsActivity.class);
      //  i3.putExtra("Lista",monumentos);
       // startActivity(i3);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnGuardar:
                String nombreM = nombreSitio.getText().toString();
                String paisM = pais.getText().toString();
                String ciudadM= ciudad.getText().toString();
                String descripcionM= descripcion.getText().toString();
                String tipo="";

                    if(rdNaturalM.isChecked())
                        tipo="Natural";

                    if(rdArtificialM.isChecked())
                         tipo="Artificial";

                    if(nombreM.equals("")||paisM.equals("")||ciudadM.equals("")||tipo.equals("")){
                        Toast.makeText(getApplicationContext(), "Rellena los campos obligatorios", Toast.LENGTH_SHORT).show();
                    }else{
                         mon = new Monumento(nombreM,paisM,ciudadM,tipo,descripcionM);
                        Toast.makeText(getApplicationContext(), mon.cod, Toast.LENGTH_SHORT).show();
                        if(!metodos.buscarMonumento(mon.cod)){
                            long id = metodos.addMonumento(mon);
                            monumentos.add(mon);
                            Toast.makeText(getApplicationContext(), "Monumento registrado", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Monumento ya esta registrado",Toast.LENGTH_SHORT).show();
                        }

                    }

                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.mnListar:
                Intent i2 = new Intent(getApplicationContext(),Listar.class);
                startActivity(i2);
                break;

            case R.id.mnEliminar:
                AlertDialog.Builder dialog2 = new AlertDialog.Builder(this);
                dialog2.setTitle("Nombre del monumento");
                //dialog3.setMessage("No se encontro elemento a borrar");
                dialog2.setCancelable(true);

                final EditText input = new EditText(this);
                input.setHeight(130);
                input.setWidth(340);
                input.setGravity(Gravity.LEFT);
                input.setImeOptions(EditorInfo.IME_ACTION_DONE);


                dialog2.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogo1, int id) {
                        String cod=  input.getText().toString();
                        metodos.eliminarMonumento(cod);
                        Intent i = new Intent(getApplicationContext(),Listar.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),"Eliminado",Toast.LENGTH_SHORT).show();


                    }
                });
                dialog2.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        Intent i = new Intent(getApplicationContext(),Listar.class);
                        startActivity(i);
                    }
                });

                dialog2.setView(input);
                dialog2.show();

                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
