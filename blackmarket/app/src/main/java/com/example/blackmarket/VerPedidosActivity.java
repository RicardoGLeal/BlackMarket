package com.example.blackmarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.blackmarket.Adapters.Adaptador_Pedidos;
import com.example.blackmarket.POJOS.Newpedido;
import com.example.blackmarket.POJOS.Pedido;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.Comparator;

public class VerPedidosActivity extends AppCompatActivity {
    RecyclerView ListaPedidos;
    Boolean car;
    Adaptador_Pedidos ap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_pedidos);

        car = getIntent().getBooleanExtra("car",false);

        Collections.sort(GLOBAL.PEDIDO, new Comparator<Pedido>() {
            @Override
            public int compare(Pedido o1, Pedido o2) {
                if(o1.getId() > o2.getId()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        ListaPedidos = findViewById(R.id.Lista_pedidos);
        getPedido();
        getPedido();
        ap = new Adaptador_Pedidos();
        ap.context = this;
        LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        ListaPedidos.setLayoutManager(llm);
        ListaPedidos.setAdapter(ap);
    }

    public void Atras(View view) {

        onBackPressed();

    }

    @Override
    public void onBackPressed() {
        if(car){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("abrirCarrito",false);
            startActivity(intent);
            finish();
        }else{
            super.onBackPressed();
        }
    }

    public void AbrirCarrito() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("abrirCarrito",true);
        startActivity(intent);
        finish();
    }

    public void getPedido(){
        RequestQueue getpedQueue;
        getpedQueue = Volley.newRequestQueue(VerPedidosActivity.this);
        JsonObjectRequest getPedJson;
        String getPedurl = "https://bmarkettest.000webhostapp.com/obtPedido.php?UserID="
                + GLOBAL.USUARIO.getId();
        getPedJson = new JsonObjectRequest(Request.Method.GET, getPedurl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("pedidojson");
                   // GLOBAL.PEDIDO.clear();
                    GLOBAL.NEWPEDIDOS.clear();
                    for (int i = 0; i< jsonArray.length(); i++) {
                        JSONObject pedidoj = jsonArray.getJSONObject(i);
                        int PedID = pedidoj.getInt("PedID");
                        int DicID = pedidoj.getInt("DicID");
                        String direccion = pedidoj.getString("direccion");
                        int cp = pedidoj.getInt("cp");
                        String fecha = pedidoj.getString("fecha");
                        int status = pedidoj.getInt("status");

                        Newpedido pedi = new Newpedido();
                        pedi.setPedID(PedID);
                        pedi.setDicID(DicID);
                        pedi.setDireccion(direccion);
                        pedi.setCP(cp);
                        pedi.setFecha(fecha);
                        pedi.setStatus(status);
                        GLOBAL.NEWPEDIDOS.add(pedi);
                       // Pedido pedi = new Pedido();
                       /* pedi.setId(PedID);
                        pedi.setFecha(fecha);
                        pedi.setIdx_dir(DicID);
                        pedi.setStatus(status);
                        GLOBAL.PEDIDO.add(pedi);
                        GLOBAL.PEDIDO.get(i).getIdx_dir()*/

                    }
                ap.notifyDataSetChanged();
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
getpedQueue.add(getPedJson);
    }
}
