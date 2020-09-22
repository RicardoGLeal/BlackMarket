package com.example.blackmarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blackmarket.Adapters.Adaptador_Carrito;

import es.dmoral.toasty.Toasty;


public class CarritoFragment extends Fragment {

    View v;
    Button comprarBoton;
    RecyclerView ListaCarrito;
    public  TextView total, direccion;
    public Adaptador_Carrito adaptador_carrito_fragment_products;
    public LinearLayout direccionOpc;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_carrito, container, false);// Inflate the layout for this fragment

        comprarBoton = v.findViewById(R.id.boton_comprar_carrito);
        ListaCarrito = v.findViewById(R.id.Lista_carrito);
        total = v.findViewById(R.id.carrito_total);

        direccionOpc = v.findViewById(R.id.carrito_direccion_opc);
        direccion = v.findViewById(R.id.carrito_direccion_seleccionada);

       if(GLOBAL.DIRECCIONS.isEmpty()){
            direccion.setText("DIRECCION");
        }else{
            direccion.setText(GLOBAL.DIRECCIONS.get(GLOBAL.INDICES.getDireccionSeleccionada()).getDireccion());
        }

        GLOBAL.CARRITO.setTotal(calcularTotal());
        total.setText("$"+GLOBAL.CARRITO.getTotal());

        comprarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comprar();
            }
        });

        adaptador_carrito_fragment_products = new Adaptador_Carrito(new Adaptador_Carrito.OnItemClickListener() {

            @Override
            public void botonPresionado() {
                GLOBAL.CARRITO.setTotal(calcularTotal());
                total.setText("$"+GLOBAL.CARRITO.getTotal());
            }

            @Override
            public void elementoEliminado() {
                adaptador_carrito_fragment_products.notifyDataSetChanged();
                GLOBAL.CARRITO.setTotal(calcularTotal());
                total.setText("$"+GLOBAL.CARRITO.getTotal());
            }
        });
        adaptador_carrito_fragment_products.context = getContext();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        ListaCarrito.setLayoutManager(linearLayoutManager);
        ListaCarrito.setAdapter(adaptador_carrito_fragment_products);


        direccionOpc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccionarDireccion();
            }
        });

        return v;
    }

    private void seleccionarDireccion() {
        Intent intent = new Intent(getContext(), DireccionesActivity.class);
        intent.putExtra("carrito",true);
        startActivity(intent);
    }

    public void Comprar(){

        if(GLOBAL.CARRITO.getTotal()<=0){
            Toast toast = Toasty.error(getContext(),"Tu carrito esta vacio!",Toast.LENGTH_SHORT,true);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();

        }else if(GLOBAL.DIRECCIONS.isEmpty()){
            Toast toast = Toasty.error(getContext(),"Selecciona una direccion!",Toast.LENGTH_SHORT,true);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }else{
            Intent intent = new Intent(getContext(), ConfirmarPedidoActivity.class);
            startActivity(intent);
        }
    }


    public int calcularTotal(){
        int x = 0;
        int n = GLOBAL.CARRITO.carrito_productos.size();
       // int i = 0;

        for(int i = 0; i < n; i++){
            x = x + (GLOBAL.CARRITO.carrito_productos.get(i).getPrecio() * (GLOBAL.CARRITO.carrito_productos2.get(i).getCantidad()));
        }
        return x;
    }

}
