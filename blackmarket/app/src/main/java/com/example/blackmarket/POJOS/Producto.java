package com.example.blackmarket.POJOS;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Producto {

    // TODO: cargar la imagen por medio de picasso

    int itemID;
    int categoria;
    String nombre, descripcion;
    int precio;
    int rating;
    String imagen;

    boolean carrito;

    int cantidad;

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isCarrito() {
        return carrito;
    }

    public void setCarrito(boolean carrito) {
        this.carrito = carrito;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
}
