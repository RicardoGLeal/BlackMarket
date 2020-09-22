package com.example.blackmarket.POJOS;

import java.util.ArrayList;

public class Carrito {
    public  ArrayList<Producto> carrito_productos = new ArrayList<>();
    public ArrayList<Producto_Cant>carrito_productos2 = new ArrayList<>();

    int total = 0;

    public  ArrayList<Producto> getCarrito_productos() {
        return carrito_productos;
    }

    public void setCarrito_productos(ArrayList<Producto> carrito_productos) {
        this.carrito_productos = carrito_productos;
    }

    public void addCarrito_productos(Producto carrito_productos) {
        this.carrito_productos.add(carrito_productos);
    }

    public  ArrayList<Producto_Cant> getCarrito_productos2() {
        return carrito_productos2;
    }

    public void setCarrito_productos2(ArrayList<Producto_Cant> carrito_productos) {
        this.carrito_productos2 = carrito_productos;
    }

    public void addCarrito_productos2(Producto_Cant carrito_productos) {
        this.carrito_productos2.add(carrito_productos);
    }

    public int getTotal() {
        return total;
    }

    public  void setTotal(int total){
        this.total = total;
    }
}
