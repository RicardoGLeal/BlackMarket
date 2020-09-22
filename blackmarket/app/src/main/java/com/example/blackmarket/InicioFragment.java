package com.example.blackmarket;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.blackmarket.Adapters.Adaptador_Categorias;
import com.example.blackmarket.Adapters.ViewPagerAdapter;
import com.example.blackmarket.POJOS.Categoria;
import com.example.blackmarket.POJOS.Producto;
import com.rd.PageIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import es.dmoral.toasty.Toasty;


public class InicioFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{

    View v;
    RecyclerView listaCategorias;
    SearchView searchView;
    Toolbar toolbar;
    ViewPager viewPager;
    Adaptador_Categorias adaptador_categorias;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_inicio, container, false);
        listaCategorias = v.findViewById(R.id.Lista_categorias);
        searchView = v.findViewById(R.id.search_bar_inicio);

        viewPager = v.findViewById(R.id.viewpager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getContext());
        viewPager.setAdapter(viewPagerAdapter);
        TabLayout tabLayout = v.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager, true);

        toolbar = v.findViewById(R.id.toolbar_inicio);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        adaptador_categorias = new Adaptador_Categorias();
        adaptador_categorias.context = getContext();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        listaCategorias.setAdapter(adaptador_categorias);
        listaCategorias.setLayoutManager(linearLayoutManager);


        /*final PopupMenu popupMenu = new PopupMenu(getContext(),searchView);

        popupMenu.inflate(R.menu.search_bar_menu);*/

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent = new Intent(getContext(), SearchProductsResultActivity.class);
                intent.putExtra("categoria",-1);
                intent.putExtra("productoNombre",s);
                getContext().startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


        LlenarCategorias();
        LlenarProductos();


        return v;
    }

    public void LlenarCategorias(){
        getCategories();
    }

    public void LlenarProductos() {
        getItems();
    }

    public void getItems()
    {

        RequestQueue itemQueue;
        itemQueue = Volley.newRequestQueue(getContext());
        String url = "https://bmarkettest.000webhostapp.com/getitems.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("itemsjson");
                    GLOBAL.PRODUCTOS.clear();
                    for (int i = 0; i< jsonArray.length(); i++) {
                        JSONObject itemj = jsonArray.getJSONObject(i);
                        int itemid = itemj.getInt("id");
                        String itemname = itemj.getString("nombre");
                        String itemdesc = itemj.getString("desc");
                        int itemcat = itemj.getInt("cat");
                        int itemprice = itemj.getInt("price");
                        String itemimage = itemj.getString("image");
                        Producto produ = new Producto();
                        produ.setNombre(itemname);
                        produ.setDescripcion(itemdesc);
                        produ.setCategoria(itemcat);
                        produ.setPrecio(itemprice);
                        produ.setImagen(itemimage);
                        produ.setItemID(itemid);
                        GLOBAL.PRODUCTOS.add(produ);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        itemQueue.add(request);
    }

    public void getCategories()
    {
        RequestQueue myQueue;
        myQueue = Volley.newRequestQueue(getContext());
        String url = "https://bmarkettest.000webhostapp.com/getcategorias.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("categoriasjson");
                            GLOBAL.CATEGORIAS.clear();
                            for (int i = 0; i< jsonArray.length(); i++) {
                                JSONObject categorias = jsonArray.getJSONObject(i);
                                int cateid = categorias.getInt("id");
                                String catenombre = categorias.getString("nombre");
                                Categoria cate = new Categoria();
                                cate.setId(cateid);
                                cate.setNombre(catenombre);
                                GLOBAL.CATEGORIAS.add(cate);
                                adaptador_categorias.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        myQueue.add(request);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast toast = Toasty.error(getContext(),"error!",Toast.LENGTH_SHORT,true);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    @Override
    public void onResponse(JSONObject response) {

    }
}
