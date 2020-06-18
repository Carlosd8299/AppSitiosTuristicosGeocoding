package com.example.parcial3ercorte;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {

    public BaseDatos(Context context) {
        super(context, nameDb, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_tabla_paciente);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        onCreate(sqLiteDatabase);
    }

    public static final String nameDb = "Monumento";
    public static final String tabla_mon     = "monumento";
    public static final String col_codigo = "codigo";
    public static final String col_nombre = "nombre";
    public static final String col_pais = "pais";
    public static final String col_ciudad = "ciudad";
    public static final String col_tipo = "tipo";
    public static final String col_descripcion = "descripcion";

    public static final String create_tabla_paciente = "CREATE TABLE IF NOT EXISTS monumento(" +
            "  codigo text primary key," +
            "  nombre text," +
            "  pais text," +
            "  ciudad text," +
            "  tipo text," +
            "  descripcion text" +
            ");";
}
