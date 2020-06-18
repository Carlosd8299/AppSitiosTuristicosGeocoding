package com.example.parcial3ercorte;

import android.os.Parcel;
import android.os.Parcelable;

public class Monumento implements Parcelable {

    String cod;
    String nombre;
    String pais;
    String ciudad;
    String tipo;
    String descripcion;

    public Monumento(String nombre, String pais, String ciudad, String tipo, String descripcion) {
        this.cod = nombre+pais+ciudad;
        this.nombre = nombre;
        this.pais = pais;
        this.ciudad = ciudad;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    protected Monumento(Parcel in) {
        cod = in.readString();
        nombre = in.readString();
        pais = in.readString();
        ciudad = in.readString();
        tipo = in.readString();
        descripcion = in.readString();
    }

    public static final Creator<Monumento> CREATOR = new Creator<Monumento>() {
        @Override
        public Monumento createFromParcel(Parcel in) {
            return new Monumento(in);
        }

        @Override
        public Monumento[] newArray(int size) {
            return new Monumento[size];
        }
    };

    @Override
    public String toString() {
        return "Monumento{" +
                "nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", tipo='" + tipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cod);
        dest.writeString(nombre);
        dest.writeString(pais);
        dest.writeString(ciudad);
        dest.writeString(tipo);
        dest.writeString(descripcion);
    }
}
