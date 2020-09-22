package com.example.blackmarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.blackmarket.Adapters.Adaptador_Categorias;
import com.example.blackmarket.Adapters.Adaptador_Productos;
import com.example.blackmarket.Adapters.Adaptador_Productos_Categorias_Filtro;

public class SearchProductsResultActivity extends AppCompatActivity {

    RecyclerView listaProductos;
    int busqueda;
    String nombreProducto;

    Adaptador_Productos adaptadorProductos;
    Adaptador_Productos_Categorias_Filtro adaptador_productos_categorias_filtro;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_products_result);
        String nose = null;

        busqueda = getIntent().getIntExtra("categoria",-1);
        nombreProducto = getIntent().getExtras().getString("productoNombre");

        toolbar = findViewById(R.id.toolbar_search_product_result);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        listaProductos = findViewById(R.id.Lista_product_result);

        adaptador_productos_categorias_filtro = new Adaptador_Productos_Categorias_Filtro();
        adaptador_productos_categorias_filtro.context = this;
        adaptador_productos_categorias_filtro.busqueda = busqueda;

        adaptadorProductos = new Adaptador_Productos();
        adaptadorProductos.context = this;
        adaptadorProductos.busqueda = busqueda;
        //adaptadorProductos.nombreProductoBuscar = nombreProducto;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);


        listaProductos.setLayoutManager(linearLayoutManager);

        if(busqueda!=-1){
            listaProductos.setAdapter(adaptador_productos_categorias_filtro);
        }else{

            adaptadorProductos.getFilter().filter(nombreProducto);
            listaProductos.setAdapter(adaptadorProductos);
        }

    }

    public void Atras(View view) {
        onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(busqueda==-1){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.search_bar_menu, menu);

            MenuItem searhItem = menu.findItem(R.id.action_search);
            SearchView searchView = (SearchView) searhItem.getActionView();

            searchView.setOnSearchClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listaProductos.setAdapter(adaptadorProductos);
                    adaptadorProductos.notifyDataSetChanged();
                }
            });
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {

                    adaptadorProductos.getFilter().filter(s);

                    return false;
                }
            });
        }
        return true;
    }
}
