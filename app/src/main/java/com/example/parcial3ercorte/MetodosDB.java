package com.example.parcial3ercorte;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MetodosDB extends AppCompatActivity {
    private BaseDatos db;

    public MetodosDB(Context context) {
        this.db = new BaseDatos(context);
    }

    public long addMonumento(Monumento monumento){
        try{
            SQLiteDatabase database = db.getWritableDatabase();
            ContentValues valores = new ContentValues();

            valores.put(BaseDatos.col_codigo,monumento.cod);
            valores.put(BaseDatos.col_nombre,monumento.nombre);
            valores.put(BaseDatos.col_ciudad,monumento.ciudad);
            valores.put(BaseDatos.col_tipo,monumento.tipo);
            valores.put(BaseDatos.col_descripcion, monumento.descripcion);
            valores.put(BaseDatos.col_pais, monumento.pais);
            long id = database.insert(BaseDatos.tabla_mon,null,valores);
            return id;
        }
        catch (Exception ex){
            System.out.println("Error al insertar");
            return 0;
        }
    }

    public boolean buscarMonumento(String cod){
        String[] args = new String[] {cod};
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor c = database.query(BaseDatos.tabla_mon, null, "nombre=?", args, null, null, null);
        if (c.getCount()>0) {
            database.close();
            return true;
        }
        else{
            database.close();
            return false;}
    }

    public long actualizarMonumento(Monumento monumento){
        try{

            SQLiteDatabase database = db.getWritableDatabase();
            ContentValues valores = new ContentValues();
            String[] args = new String[] {monumento.cod};

            valores.put(BaseDatos.col_nombre,monumento.nombre);
            valores.put(BaseDatos.col_ciudad,monumento.ciudad);
            valores.put(BaseDatos.col_tipo,monumento.tipo);
            valores.put(BaseDatos.col_descripcion, monumento.descripcion);
            valores.put(BaseDatos.col_pais, monumento.pais);
            long id = database.update(BaseDatos.tabla_mon, valores,"codigo=?",args);
            database.close();
            return id;
        }
        catch (Exception ex){
            System.out.println("Error al actualizar");
            return 0;
        }

    }

    public Cursor allMonumentos() {
        try {
            SQLiteDatabase database = db.getWritableDatabase();
            Cursor cur = database.rawQuery("select codigo as _id , nombre,pais,ciudad,tipo,descripcion  from monumento", null);
            return cur;
        } catch (Exception ex) {
            System.out.println("Error al consultar");
            return null;
        }
    }

    public Cursor devolverMonumento(String nombre){
        try {
            String[] args = new String[] {nombre};
            SQLiteDatabase database = db.getReadableDatabase();
            // Cursor c = database.query(DefDB.tabla_persona, null, "codigo=?", args, null, null, null);
            Cursor c=   database.rawQuery("select codigo as _id , nombre,pais,ciudad,tipo,descripcion  from monumento where nombre=?",args);
            return c;
        }catch (Exception ex) {
            System.out.println("Error al consultar");
            return null;
        }

    }

    public long eliminarMonumento(String nombre) {

        try {
            if (buscarMonumento(nombre)) {
                String[] args = new String[]{nombre};
                SQLiteDatabase database = db.getWritableDatabase();
                long id = database.delete(BaseDatos.tabla_mon, "nombre=?", args);
                database.close();
                return id;

            } else {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Importante");
                dialog.setMessage("No se encontro elemento a borrar");
                return 0;
            }

        } catch (Exception ex) {
            return 0;
        }
    }

}
