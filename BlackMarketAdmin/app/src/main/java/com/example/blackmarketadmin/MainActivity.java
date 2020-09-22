package com.example.blackmarketadmin;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.blackmarketadmin.POJOS.Categoria;
import com.example.blackmarketadmin.POJOS.Producto;
import com.example.blackmarketadmin.POJOS.Usuarios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView t;

    @Override
    protected void onResume() {
        super.onResume();
        FragmentManager fragmentManager = getSupportFragmentManager();
        //categoryFragment aFragment=fragmentManager.findFragmentByTag("Place the History fragment TAG here which you have used to load before");
    }

    RequestQueue myQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myQueue = Volley.newRequestQueue(this);

        Fragment selectecFragment = new categoryFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                selectecFragment).commit();
        //getCategories();
        //   LlenarCategorias();
        //LlenarProductos();
        //LlenarUsuarios();
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav);

        //es un OnClick pero de BottonNavigation, es para el manejo de que fragment mostrar
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        // Pone el fragment de inicio al abrirse la app
    }

    public void getCategories() {
        String url = "https://bmarkettest.000webhostapp.com/getcategorias.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("categoriasjson");
                           for (int i = 0; i< jsonArray.length(); i++)
                           {
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

    private void LlenarUsuarios() {
        Usuarios usuario = new Usuarios();
        usuario.setName("Ricardo");
        usuario.setLastname("Gonzalez Leal");
        usuario.setEmail("ricardoglr664@gmail.com");
        usuario.setPhone("3310851164");
        usuario.setId(0);
        usuario.setPassword("asd123");
        GLOBAL.USUARIOS.add(usuario);

        Usuarios usuario2 = new Usuarios();
        usuario2.setName("Angel");
        usuario2.setLastname("Padilla Esqueda");
        usuario2.setEmail("padilla.angel88@gmail.com");
        usuario2.setPhone("1234567890");
        usuario2.setId(1);
        usuario2.setPassword("asd123");
        GLOBAL.USUARIOS.add(usuario2);
    }

    public void LlenarCategorias(){
        Categoria categoria = new Categoria();
        Categoria categoria1 = new Categoria();
        Categoria categoria2 = new Categoria();
        Categoria categoria3 = new Categoria();

        categoria.setNombre("Electronicos");
        categoria1.setNombre("Libros");
        categoria2 .setNombre("Mascotas");
        categoria3.setNombre("Videojuegos");

        categoria.setId(0);
        categoria1.setId(1);
        categoria2.setId(2);
        categoria3.setId(3);

        GLOBAL.CATEGORIAS.add(categoria);
        GLOBAL.CATEGORIAS.add(categoria1);
        GLOBAL.CATEGORIAS.add(categoria2);
        GLOBAL.CATEGORIAS.add(categoria3);
    }

    public void LlenarProductos() {
        Producto producto = new Producto();
        Producto producto1 = new Producto();
        //Producto carritoP = new Producto();
        //GLOBAL.CARRITO.carrito_productos.add(carritoP);
        producto.setItemID(1);
        producto.setNombre("Xbox One S");
        producto.setDescripcion("Con el paquete Xbox One S Tom Clancy's The Division 2 (1 TB), pon el destino de la nación en tus manos. Lidera un equipo de agentes de élite para salvar un país que está a punto de colapsar mientras exploras el mundo abierto, dinámico y hostil de Washington, D.C. Postpandémico. Este paquete incluye la descarga completa del juego Tom Clancy's, The Division 2, un mes de suscripción de Xbox Live Gold y un mes de prueba de Xbox Game Pass. Con un verdadero juego 4K y 40% más potencia que cualquier otra consola, nunca ha habido un mejor momento para jugar con Xbox One.");
        producto.setCategoria(3);
        producto.setPrecio(5199);
        producto.setImagen("https://compass-ssl.xbox.com/assets/05/b0/05b01a46-58eb-4927-ad21-3c43b545ebaf.jpg?n=X1S-2019_Panes-2-Up-1084_111_570x400.jpg");
        //producto.setCantidad(1);
        producto.setCarrito(false);

        producto1.setItemID(2);
        producto1.setNombre("Skullcandy Audífonos Bluetooth");
        producto1.setDescripcion("Características\n" +
                "Los estéreo Haptics entregan fuertes bajos direccionales a cada oído.\n" +
                "\n" +
                "Los controladores de audio proporcionan un sonido potente y refinado para todo tipo de música.\n" +
                "\n" +
                "Hasta 40 horas de batería.");
        producto1.setCategoria(0);
        producto1.setPrecio(2591);
        producto1.setImagen("https://images-na.ssl-images-amazon.com/images/I/71FDTyuVf3L._SL1500_.jpg");
        //producto1.setCantidad(1);
        producto1.setCarrito(false);

        GLOBAL.PRODUCTOS.add(producto);
        GLOBAL.PRODUCTOS.add(producto1);

    }

    public  BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    //hace el cambio de fragment dependiendo de cual boton fue pulsado
                    Fragment selectecFragment = null;
                    switch (menuItem.getItemId()){
                        case R.id.nav_categorias:
                            selectecFragment = new categoryFragment();
                            break;
                        case R.id.nav_articulos:
                            selectecFragment = new itemFragment();
                            break;
                        case R.id.nav_usuarios:
                            selectecFragment = new usersFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectecFragment).commit();

                    return true;
                }
            };
}
