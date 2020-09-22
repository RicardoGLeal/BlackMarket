package com.example.blackmarket.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blackmarket.GLOBAL;
import com.example.blackmarket.R;
import com.squareup.picasso.Picasso;

public class Adaptador_Prepedido extends RecyclerView.Adapter<Adaptador_Prepedido.Pruebaminiactivity>
{
    public Context context;

    @NonNull
    @Override
    public Pruebaminiactivity onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = View.inflate(context, R.layout.item_prepedido, null);
        Pruebaminiactivity pruebaminiactivity = new Pruebaminiactivity(v);
        return pruebaminiactivity;
    }

    @Override
    public void onBindViewHolder(@NonNull Pruebaminiactivity pruebaminiactivity, int i) {
        final int indice = i;
       pruebaminiactivity.Pruebanombre.setText(GLOBAL.CARRITO.carrito_productos.get(indice).getNombre());
       pruebaminiactivity.Pruebacantidad.setText(""+GLOBAL.CARRITO.carrito_productos2.get(indice).getCantidad());
       pruebaminiactivity.Pruebaprecio.setText("$"+GLOBAL.CARRITO.carrito_productos.get(indice).getPrecio());
        Picasso.get()
                .load(GLOBAL.CARRITO.carrito_productos.get(indice).getImagen())
                .into(pruebaminiactivity.Pruebaimagen);
    }

    @Override
    public int getItemCount() {
        return GLOBAL.CARRITO.carrito_productos.size();
    }

    public class Pruebaminiactivity extends RecyclerView.ViewHolder {
        public TextView Pruebanombre, Pruebacantidad, Pruebaprecio;
        public ImageView Pruebaimagen;
        public Pruebaminiactivity(@NonNull View itemView) {
            super(itemView);
            Pruebanombre = itemView.findViewById(R.id.item_prepedido_nombre);
            Pruebacantidad = itemView.findViewById(R.id.item_prepedido_cantidad);
            Pruebaprecio = itemView.findViewById(R.id.item_prepedido_precio);
            Pruebaimagen = itemView.findViewById(R.id.item_prepedido_imagen);
        }
    }
}
