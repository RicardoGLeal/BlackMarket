package com.example.blackmarket.Adapters;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.blackmarket.GLOBAL;
import com.example.blackmarket.POJOS.Producto;
import com.example.blackmarket.ProductInfoActivity;
import com.example.blackmarket.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Adaptador_Productos extends RecyclerView.Adapter<Adaptador_Productos.ProductoActivity> implements Filterable {
    public Context context;
    private List<Producto> temp_productos;
    private List<Producto> fullproductos;

    public int busqueda;
    public String nombreProductoBuscar;

    public Adaptador_Productos(){
        fullproductos = new ArrayList<>(GLOBAL.PRODUCTOS);
        temp_productos = new ArrayList<>(GLOBAL.PRODUCTOS);
    }

    @NonNull
    @Override
    public ProductoActivity onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = View.inflate(context, R.layout.item_product, null);
        ProductoActivity productoActivity = new ProductoActivity(v);
        return productoActivity;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoActivity productoActivity, final int i) {
         final int indice = i;

        productoActivity.productoNombre.setText(fullproductos.get(indice).getNombre());
        productoActivity.productoPrecio.setText("$"+fullproductos.get(indice).getPrecio());
        productoActivity.productoRating.setRating(fullproductos.get(indice).getRating());

        Picasso.get()
                .load(fullproductos.get(indice).getImagen())
                .into(productoActivity.productoImagen);




        productoActivity.productoNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductInfoActivity.class);
                intent.putExtra("indice",fullproductos.get(indice).getItemID());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        //return temp_productos.size();
        //return GLOBAL.PRODUCTOS.size();
        return fullproductos.size();
    }

    @Override
    public Filter getFilter() {
        return filtro;
    }

    private Filter filtro = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Producto> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length()==0){
                filteredList.addAll(temp_productos);
            }else{

                String fillterPartten = constraint.toString().toLowerCase().trim();

                if(busqueda != -1){
                    for(Producto item : temp_productos){
                        if(item.getCategoria() == busqueda){
                            filteredList.add(item);
                        }
                    }
                }else{
                    for(Producto item : temp_productos){
                        if(item.getNombre().toLowerCase().contains(fillterPartten)){
                            filteredList.add(item);
                        }
                    }
                }


            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            fullproductos.clear();
            fullproductos.addAll((List) results.values);
            notifyDataSetChanged();

            /*temp_productos.clear();
            temp_productos.addAll((List) results.values);
            notifyDataSetChanged();*/
        }
    };

    public class ProductoActivity extends RecyclerView.ViewHolder {

        public TextView productoNombre;
        public TextView productoPrecio;
        public ImageView productoImagen;
        public RatingBar productoRating;
        public CardView container;

        public ProductoActivity(@NonNull View itemView) {
            super(itemView);

            productoNombre = itemView.findViewById(R.id.item_product_nombre);
            productoPrecio = itemView.findViewById(R.id.item_product_precio);
            productoImagen = itemView.findViewById(R.id.item_product_imagen);
            productoRating = itemView.findViewById(R.id.item_product_rating);
            container = itemView.findViewById(R.id.item_product);
        }
    }
}
