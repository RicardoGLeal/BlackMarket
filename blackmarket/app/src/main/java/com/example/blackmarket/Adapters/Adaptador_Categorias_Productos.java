package com.example.blackmarket.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.blackmarket.GLOBAL;
import com.example.blackmarket.ProductInfoActivity;
import com.example.blackmarket.R;
import com.squareup.picasso.Picasso;

public class Adaptador_Categorias_Productos extends RecyclerView.Adapter<Adaptador_Categorias_Productos.ProductoCategoriaActivity> {
    public Context context;
    public int cat;

    @NonNull
    @Override
    public ProductoCategoriaActivity onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_categoria_producto,null);
        ProductoCategoriaActivity productoCategoriaActivity = new ProductoCategoriaActivity(view);
        return productoCategoriaActivity;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoCategoriaActivity productoCategoriaActivity, int i) {
        final int indice = i;

        if(GLOBAL.PRODUCTOS.get(indice).getCategoria() == cat){
            Picasso.get()
                    .load(GLOBAL.PRODUCTOS.get(indice).getImagen())
                    .into(productoCategoriaActivity.imagen);
            productoCategoriaActivity.precio.setText("$"+GLOBAL.PRODUCTOS.get(indice).getPrecio());
        }else{
            productoCategoriaActivity.contenedor.setVisibility(View.GONE);
        }

        productoCategoriaActivity.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductInfoActivity.class);
                intent.putExtra("indice",GLOBAL.PRODUCTOS.get(indice).getItemID());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        //int n = 0;

        /*for(int i = 0; i < GLOBAL.PRODUCTOS.size(); i++){
            if(cat == GLOBAL.PRODUCTOS.get(i).getCategoria()){
                n++;
            }
        }*/

        return GLOBAL.PRODUCTOS.size();
    }

    public class ProductoCategoriaActivity extends RecyclerView.ViewHolder {
        public ImageView imagen;
        public TextView precio;
        public LinearLayout contenedor;
        public ProductoCategoriaActivity(@NonNull View itemView) {
            super(itemView);

            imagen = itemView.findViewById(R.id.item_categoria_producto_imagen);
            precio = itemView.findViewById(R.id.item_categoria_producto_precio);
            contenedor = itemView.findViewById(R.id.contenedor_producto_categoria);
        }
    }
}