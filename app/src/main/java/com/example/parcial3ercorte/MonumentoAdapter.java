package com.example.parcial3ercorte;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class MonumentoAdapter extends CursorAdapter {

    public MonumentoAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.dato_monumento, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nombres = (TextView) view.findViewById(R.id.txtnombre);
        TextView descripcion = (TextView) view.findViewById(R.id.txtdescri);
        TextView tipo = (TextView) view.findViewById(R.id.txttipo);
        TextView ciudad = (TextView) view.findViewById(R.id.txtciudad);
        TextView pais = (TextView) view.findViewById(R.id.txtpais);


        // Extract properties from cursor
        String nombreS = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
        String ciudadS = cursor.getString(cursor.getColumnIndexOrThrow("ciudad"));
        String paisS= cursor.getString(cursor.getColumnIndexOrThrow("pais"));
        String tipoS= cursor.getString(cursor.getColumnIndexOrThrow("tipo"));
        String descripcionS= cursor.getString(cursor.getColumnIndexOrThrow("descripcion"));
        // Populate fields with extracted properties
        nombres.setText(nombreS);
        descripcion.setText(descripcionS);
        tipo.setText(tipoS);
        ciudad.setText(ciudadS);
        pais.setText(paisS);

    }
}
