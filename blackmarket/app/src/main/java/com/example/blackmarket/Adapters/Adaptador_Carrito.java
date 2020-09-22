package com.example.blackmarket.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blackmarket.ConfirmarPedidoActivity;
import com.example.blackmarket.GLOBAL;
import com.example.blackmarket.ProductInfoActivity;
import com.example.blackmarket.R;
import com.squareup.picasso.Picasso;

public class Adaptador_Carrito extends RecyclerView.Adapter<Adaptador_Carrito.CarritoItemActivity> {
    public Context context;
    private final OnItemClickListener listener;


    //Listener que nos va a avisar cuando se precione el boton de + o - en cualquier objeto
    public interface OnItemClickListener{
        //void onItemClick();
        void botonPresionado();
        void elementoEliminado();
    }

    //Constructor
    public Adaptador_Carrito(OnItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public CarritoItemActivity onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = View.inflate(context, R.layout.item_carrito,null);
        CarritoItemActivity carritoItemActivity = new CarritoItemActivity(v);
        return carritoItemActivity;
    }

    @Override
    public void onBindViewHolder(@NonNull final CarritoItemActivity carritoItemActivity, int i) {
        final int indice = i;

        carritoItemActivity.productoNombre.setText(GLOBAL.CARRITO.carrito_productos.get(indice).getNombre());
        carritoItemActivity.productoPrecio.setText("$"+GLOBAL.CARRITO.carrito_productos.get(indice).getPrecio());
        Picasso.get()
                .load(GLOBAL.CARRITO.carrito_productos.get(indice).getImagen())
                .into(carritoItemActivity.productoImagen);
        carritoItemActivity.cantidad.setText(""+GLOBAL.CARRITO.carrito_productos2.get(indice).getCantidad());

        carritoItemActivity.masCant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              mas(carritoItemActivity, indice);
                GLOBAL.CARRITO.setTotal(calcularTotal());
                listener.botonPresionado();
            }
        });
        carritoItemActivity.menosCant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menos(carritoItemActivity, indice);
                GLOBAL.CARRITO.setTotal(calcularTotal());
                listener.botonPresionado();
            }
        });
        carritoItemActivity.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GLOBAL.CARRITO.carrito_productos.get(indice).setCarrito(false);
                GLOBAL.CARRITO.carrito_productos.remove(indice);
                GLOBAL.CARRITO.carrito_productos2.remove(indice);

                listener.elementoEliminado();
            }
        });

        carritoItemActivity.productoNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductInfoActivity.class);
                int n = GLOBAL.PRODUCTOS.indexOf(GLOBAL.CARRITO.carrito_productos.get(indice));
                intent.putExtra("indice",n);
                context.startActivity(intent);
            }
        });
        carritoItemActivity.productoImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductInfoActivity.class);
                int n = GLOBAL.PRODUCTOS.indexOf(GLOBAL.CARRITO.carrito_productos.get(indice));
                intent.putExtra("indice",n);
                context.startActivity(intent);
            }
        });
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

    private void menos(@NonNull CarritoItemActivity carritoItemActivity, int indice) {
        int n = Integer.parseInt(carritoItemActivity.cantidad.getText().toString());
        if(n >= 2){
            n--;
        }
        carritoItemActivity.cantidad.setText(""+n);
        GLOBAL.CARRITO.carrito_productos2.get(indice).setCantidad(n);
        GLOBAL.CARRITO.setTotal(GLOBAL.CARRITO.getTotal()+(n * GLOBAL.CARRITO.carrito_productos.get(indice).getPrecio()));

    }

    private void mas(@NonNull CarritoItemActivity carritoItemActivity, int indice) {
        int n = Integer.parseInt(carritoItemActivity.cantidad.getText().toString());
        n++;
        carritoItemActivity.cantidad.setText(""+n);
        GLOBAL.CARRITO.carrito_productos2.get(indice).setCantidad(n);
        GLOBAL.CARRITO.setTotal(GLOBAL.CARRITO.getTotal()+(n * GLOBAL.CARRITO.carrito_productos.get(indice).getPrecio()));

    }

    @Override
    public int getItemCount() {
        return GLOBAL.CARRITO.getCarrito_productos().size();
    }

    public class CarritoItemActivity extends RecyclerView.ViewHolder {

        public TextView productoNombre;
        public TextView productoPrecio;
        public ImageView productoImagen;
        public CardView container;

        public EditText cantidad;
        public Button masCant, menosCant;
        public ImageButton delete;

       // public Button comprarBoton;
       // public  TextView total;
       // public CardView container2;

        public CarritoItemActivity(@NonNull View itemView) {
            super(itemView);

            productoImagen = itemView.findViewById(R.id.carrito_item_imagen);
            productoNombre = itemView.findViewById(R.id.carrito_item_nombre);
            productoPrecio = itemView.findViewById(R.id.carrito_item_precio);
            cantidad = itemView.findViewById(R.id.procuct_cantidad_carrito);
            container = itemView.findViewById(R.id.container_item_carrito);
            masCant = itemView.findViewById(R.id.mas_cantidad_carrito);
            menosCant = itemView.findViewById(R.id.menos_cantidad_carrito);
            delete = itemView.findViewById(R.id.delete_item_carrito);

        }

    }

}



