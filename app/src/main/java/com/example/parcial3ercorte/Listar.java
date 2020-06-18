package com.example.parcial3ercorte;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

public class Listar extends AppCompatActivity {

    MetodosDB c;
    ListView listado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar2);
        listado = findViewById(R.id.listado);

        c = new MetodosDB(getApplicationContext());

        Cursor cur = c.allMonumentos();

        MonumentoAdapter eca = new MonumentoAdapter(this,cur,0);
        listado.setAdapter(eca);
        eca.notifyDataSetChanged();


    }
}
