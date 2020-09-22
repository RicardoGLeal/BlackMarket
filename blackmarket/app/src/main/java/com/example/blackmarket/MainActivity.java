package com.example.blackmarket;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.blackmarket.POJOS.Categoria;
import com.example.blackmarket.POJOS.Direccion;
import com.example.blackmarket.POJOS.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    TextView t;
    ProgressDialog progreso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t = findViewById(R.id.carrito_total);

        Boolean abrirCarrito = getIntent().getBooleanExtra("abrirCarrito",false);


        BottomNavigationView bottomNavigationView = findViewById(R.id.nav);

        //es un OnClick pero de BottonNavigation, es para el manejo de que fragment mostrar
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        if(GLOBAL.inicio.isX()!=1){
            LlenarCategorias();
            LlenarProductos();
            getDirecciones();
            GLOBAL.inicio.setX(1);
        }

        // Pone el fragment de inicio al abrirse la app
        if(!abrirCarrito){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new InicioFragment()).commit();

            bottomNavigationView.setSelectedItemId(R.id.nav_inicio);
        }else{
            CarritoFragment carritoFragment = new CarritoFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container,carritoFragment);
            transaction.commit();
            bottomNavigationView.setSelectedItemId(R.id.nav_carrito);
        }
    }



    public void LlenarCategorias(){
        getCategories();
    }

    public void LlenarProductos() {
        getItems();
    }

    public  BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    //hace el cambio de fragment dependiendo de cual boton fue pulsado
                    Fragment selectecFragment = null;

                    switch (menuItem.getItemId()){
                        case R.id.nav_inicio:
                            selectecFragment = new InicioFragment();
                            break;
                        case R.id.nav_carrito:
                            selectecFragment = new CarritoFragment();
                            break;
                        case R.id.nav_cuenta:
                            selectecFragment = new CuentaFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectecFragment).commit();

                    return true;
                }
            };

    @Override
    public void onBackPressed() {

    }


    public void getItems()
    {
        //progreso = new ProgressDialog(this);
        //progreso.setMessage("Cargando...");
        //progreso.show();

        RequestQueue itemQueue;
        itemQueue = Volley.newRequestQueue(this);
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
                        progreso.hide();
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
        myQueue = Volley.newRequestQueue(this);
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

    public void getDirecciones()
    {
        progreso = new ProgressDialog(this);
        progreso.setMessage("Cargando...");
        progreso.show();
        RequestQueue getqueue;
        getqueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest getjson;
        String url = "https://bmarkettest.000webhostapp.com/getDirecciones.php?usuario="+GLOBAL.USUARIO.getId();
        url.replace(" ", "%20");

        getjson = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("itemsjson");
                    GLOBAL.DIRECCIONS.clear();
                    for (int i = 0; i< jsonArray.length(); i++) {
                        JSONObject itemj = jsonArray.getJSONObject(i);

                        int direccionid = itemj.getInt("dicid");
                        String direccion = itemj.getString("direccion");
                        String ciudad = itemj.getString("ciudad");
                        String estadp = itemj.getString("estado");
                        String cp = itemj.getString("cp");

                        Direccion nuevDireccion = new Direccion();

                        nuevDireccion.setDireccion(direccion);
                        nuevDireccion.setId(direccionid);
                        nuevDireccion.setCiudad(ciudad);
                        nuevDireccion.setEstado(estadp);
                        nuevDireccion.setCp(cp);
                        nuevDireccion.setSeleccionada(false);
                        /*if(GLOBAL.DIRECCIONS.isEmpty()){
                            nuevDireccion.setSeleccionada(true);
                        }else {
                            nuevDireccion.setSeleccionada(false);
                        }*/

                        GLOBAL.DIRECCIONS.add(nuevDireccion);
                        //listaDirecciones.setAdapter(adaptador_direcciones);
                      //  adaptador_direcciones.notifyDataSetChanged();

                    }

                    //adaptador_direcciones.notifyDataSetChanged();
                    progreso.hide();

                } catch (JSONException e) {
                    e.printStackTrace();
                    progreso.hide();
                    GLOBAL.DIRECCIONS.clear();
                    //adaptador_direcciones.notifyDataSetChanged();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progreso.hide();
                GLOBAL.DIRECCIONS.clear();
                //adaptador_direcciones.notifyDataSetChanged();

            }
        });
        getqueue.add(getjson);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast toast = Toasty.error(this,"error!",Toast.LENGTH_SHORT,true);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    @Override
    public void onResponse(JSONObject response) {

        progreso.hide();
        Toast toast = Toasty.success(this,"Registrado!",Toast.LENGTH_SHORT,true);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
