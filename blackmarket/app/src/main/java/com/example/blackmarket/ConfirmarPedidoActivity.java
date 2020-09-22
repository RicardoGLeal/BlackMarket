package com.example.blackmarket;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.blackmarket.Adapters.Adaptador_Prepedido;
import com.example.blackmarket.POJOS.Direccion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import es.dmoral.toasty.Toasty;

public class ConfirmarPedidoActivity extends AppCompatActivity {

    Button pagarBoton;
    TextView Nombre;
    public TextView Total;
    TextView CP, fecha;
    TextView Direccion;
    RecyclerView prepedidoRV;

    int PEDIDOID;
    String date;
    ProgressDialog progreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);

        prepedidoRV = findViewById(R.id.Lista_confirmar_pedido);
        Total = findViewById(R.id.confirmar_pedido_total);
        CP = findViewById(R.id.confirmar_pedido_cp);
        Direccion = findViewById(R.id.confirmar_pedido_direccion);
        Nombre = findViewById(R.id.confirmar_pedido_nombre);
        fecha = findViewById(R.id.confirmar_pedido_fecha);

        CP.setText(GLOBAL.DIRECCIONS.get(GLOBAL.INDICES.getDireccionSeleccionada()).getCp());
        Direccion.setText(GLOBAL.DIRECCIONS.get(GLOBAL.INDICES.getDireccionSeleccionada()).getDireccion());
        Nombre.setText(GLOBAL.USUARIO.getNombre() + " " + GLOBAL.USUARIO.getApellidos());

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm:ss");
        date = df.format(Calendar.getInstance().getTime());
        fecha.setText(date);

        /*  Adaptador_Prepedido ac = new Adaptador_Prepedido();*/


    //    getPedido();
        Adaptador_Prepedido ac = new Adaptador_Prepedido();
        ac.context = this;
        LinearLayoutManager llm =
                new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL,
                        false);
        prepedidoRV.setLayoutManager(llm);
        prepedidoRV.setAdapter(ac);

        int posicion;
        posicion = getIntent().getIntExtra("posicion", -1);
        Total.setText("$"+GLOBAL.CARRITO.getTotal());
        pagarBoton = findViewById(R.id.confirmar_pedido_pagar_boton);
        pagarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pagar();
            }
        });
    }

    public void Pagar(){
        insertarPedido();
        //GLOBAL.CARRITO.carrito_productos.clear();
        //GLOBAL.CARRITO.carrito_productos2.clear();

        Intent intent = new Intent(getApplicationContext(),VerPedidosActivity.class);
        intent.putExtra("car",true);
        startActivity(intent);
        finish();
 /* Pedido nuevoPedido = new Pedido();

                    nuevoPedido.Pedido_productos.addAll(GLOBAL.CARRITO.carrito_productos);
                    nuevoPedido.Pedido_productos2.addAll(GLOBAL.CARRITO.carrito_productos2);
                    nuevoPedido.setTotal(GLOBAL.CARRITO.getTotal());
                    nuevoPedido.setId(GLOBAL.tempId.getN());
                    nuevoPedido.setStatus(1);
                    nuevoPedido.setFecha(fecha.getText().toString());
                    nuevoPedido.setIdx_dir(GLOBAL.INDICES.getDireccionSeleccionada());

                    GLOBAL.tempId.setN(GLOBAL.tempId.getN() + 1);
                    GLOBAL.PEDIDO.add(nuevoPedido);

                    for(int i = 0; i<GLOBAL.CARRITO.carrito_productos.size();i++){
                        GLOBAL.CARRITO.carrito_productos.get(i).setCarrito(false);
                    }
                    GLOBAL.CARRITO.carrito_productos.clear();
                    GLOBAL.CARRITO.carrito_productos2.clear();

                    Intent intent = new Intent(getApplicationContext(),VerPedidosActivity.class);
                    intent.putExtra("car",true);
                    startActivity(intent);
                    finish();*/
    }



    public void insertarDescripcionPedido(){
        RequestQueue addrequest;
        JsonObjectRequest addjsonObjectRequest;

        int subtotal =0;
        addrequest = Volley.newRequestQueue(ConfirmarPedidoActivity.this);

        for(int i = 0; i< GLOBAL.CARRITO.carrito_productos.size();i++){
            subtotal = GLOBAL.CARRITO.carrito_productos.get(i).getPrecio()*GLOBAL.CARRITO.carrito_productos2.get(i).getCantidad();

            String server_url = "https://bmarkettest.000webhostapp.com/setDescripcion_pedido.php?PedID="+PEDIDOID
                    +"&itemID="+GLOBAL.CARRITO.carrito_productos.get(i).getItemID()
                    +"&Quant="+GLOBAL.CARRITO.carrito_productos2.get(i).getCantidad()
                    +"&subtotal="+subtotal;

            server_url.replace(" ","%20");

            addjsonObjectRequest = new JsonObjectRequest(Request.Method.GET, server_url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    progreso.hide();
                    Toast toast = Toasty.success(ConfirmarPedidoActivity.this,"Pedido creado correctamente",Toast.LENGTH_SHORT,true);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast toast = Toasty.success(ConfirmarPedidoActivity.this,"Pedido creado correctamente",Toast.LENGTH_SHORT,true);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    error.printStackTrace();
                }
            });
            addrequest.add(addjsonObjectRequest);
        }
    }

    public void insertarPedido(){
        RequestQueue insPedQueue;
        insPedQueue = Volley.newRequestQueue(ConfirmarPedidoActivity.this);
        final JsonObjectRequest insPedJson;
        String insPedUrl= "https://bmarkettest.000webhostapp.com/setPedido.php?" +
                "UserID=" + GLOBAL.USUARIO.getId()+
                "&TotalPrice=" + GLOBAL.CARRITO.getTotal()+
                "&Fecha=" + date +
                "&Status=1"+
                "&DicUserID="+ ((GLOBAL.DIRECCIONS.get(GLOBAL.INDICES.getDireccionSeleccionada()).getId()));
        insPedUrl.replace(" ","%20");

        insPedJson = new JsonObjectRequest(Request.Method.GET, insPedUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("pedidojson");
                    for (int i = 0; i< jsonArray.length(); i++) {
                        JSONObject pedidjson = jsonArray.getJSONObject(i);
                        PEDIDOID = pedidjson.getInt("PedID");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast toast = Toasty.success(ConfirmarPedidoActivity.this,"Pedido creado registrado correctamente",Toast.LENGTH_SHORT,true);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                insertarDescripcionPedido();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toasty.success(ConfirmarPedidoActivity.this,"Pedido creado registrado correctamente",Toast.LENGTH_SHORT,true);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                error.printStackTrace();
            }
        });

        insPedQueue.add(insPedJson);
    }



    public void Atras(View view) {
        onBackPressed();
    }
}