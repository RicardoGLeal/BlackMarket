package com.example.blackmarket;

import com.example.blackmarket.POJOS.Carrito;
import com.example.blackmarket.POJOS.Categoria;
import com.example.blackmarket.POJOS.Direccion;
import com.example.blackmarket.POJOS.INDICES;
import com.example.blackmarket.POJOS.Newpedido;
import com.example.blackmarket.POJOS.Pedido;
import com.example.blackmarket.POJOS.Producto;
import com.example.blackmarket.POJOS.Prueba;
import com.example.blackmarket.POJOS.TempID;
import com.example.blackmarket.POJOS.Usuario;

import java.util.ArrayList;

public class GLOBAL {
    //final static ArrayList<Usuario> USUARIOS = new ArrayList<>();

    public final static ArrayList<Producto> PRODUCTOS = new ArrayList<>();
    public final static ArrayList<Categoria> CATEGORIAS = new ArrayList<>();
    public final static Carrito CARRITO = new Carrito();
    public final static ArrayList<Pedido> PEDIDO = new ArrayList<>();
    public final static ArrayList<Direccion> DIRECCIONS = new ArrayList<>();
    public final static ArrayList<Newpedido> NEWPEDIDOS = new ArrayList<>();


    public final static Usuario USUARIO = new Usuario();

    public final static INDICES INDICES = new INDICES();

    public final static Prueba inicio = new Prueba();

    public final static TempID tempId = new TempID();

    public void GLOBAL(){
        inicio.setX(0);
        tempId.setN(0);
        INDICES.setDireccionSeleccionada(0);
    }
}
