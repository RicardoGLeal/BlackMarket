package com.example.blackmarket.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.blackmarket.GLOBAL;
import com.example.blackmarket.R;
import com.example.blackmarket.SearchProductsResultActivity;

public class Adaptador_Categorias extends RecyclerView.Adapter<Adaptador_Categorias.CategoriaActivity> {
    public Context context;

    @NonNull
    @Override
    public CategoriaActivity onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = View.inflate(context, R.layout.item_categoria,null);
        CategoriaActivity categoriaActivity = new CategoriaActivity(v,i);
        return categoriaActivity;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaActivity categoriaActivity, final int i) {
        final int indice = i;

        categoriaActivity.categoriaNombre.setText(GLOBAL.CATEGORIAS.get(indice).getNombre());

        categoriaActivity.categoriaNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SearchProductsResultActivity.class);
                intent.putExtra("categoria",GLOBAL.CATEGORIAS.get(indice).getId());
                context.startActivity(intent);
            }
        });

        categoriaActivity.adaptador_categorias_productos.context = context;
        categoriaActivity.adaptador_categorias_productos.cat = GLOBAL.CATEGORIAS.get(indice).getId();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        categoriaActivity.listaProductos.setAdapter(categoriaActivity.adaptador_categorias_productos);
        categoriaActivity.listaProductos.setLayoutManager(linearLayoutManager);

    }

    @Override
    public int getItemCount() {
        return GLOBAL.CATEGORIAS.size();
    }

    public class CategoriaActivity extends RecyclerView.ViewHolder {
        public TextView categoriaNombre;
        public RecyclerView listaProductos;
        public Adaptador_Categorias_Productos adaptador_categorias_productos;

        public CategoriaActivity(@NonNull View itemView, int i) {
            super(itemView);

            categoriaNombre = itemView.findViewById(R.id.item_categoria_categoria);
            listaProductos = itemView.findViewById(R.id.lista_categoria_productos);

            adaptador_categorias_productos = new Adaptador_Categorias_Productos();

            /*Adaptador_Categorias_Productos adaptador_categorias_productos = new Adaptador_Categorias_Productos();
            adaptador_categorias_productos.context = context;
            adaptador_categorias_productos.cat = GLOBAL.CATEGORIAS.get(i).getId();

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);

            listaProductos.setLayoutManager(linearLayoutManager);
            listaProductos.setAdapter(adaptador_categorias_productos);*/
        }
    }
}

