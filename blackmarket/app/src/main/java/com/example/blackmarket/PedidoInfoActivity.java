package com.example.blackmarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.blackmarket.Adapters.Adaptador_PedidoInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class PedidoInfoActivity extends AppCompatActivity {
    RecyclerView ListaPedidoInfo;
    TextView pedidoTotal, nombre, direccion, cp, fecha;
    Button cancelar;
    int sizes;
    int indice, PedID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_info);

        indice = getIntent().getIntExtra("indice",-1);
        PedID = getIntent().getIntExtra("PedID", -1);

        pedidoTotal = findViewById(R.id.pedido_info_total);
        ListaPedidoInfo = findViewById(R.id.Lista_pedido_info);
        cancelar = findViewById(R.id.pedido_info_cancelar_boton);
        nombre = findViewById(R.id.pedido_info_nombre);
        direccion = findViewById(R.id.pedido_info_direccion);
        cp = findViewById(R.id.pedido_info_cp);
        fecha = findViewById(R.id.pedido_info_fecha);


        nombre.setText(GLOBAL.USUARIO.getNombre() + " "+ GLOBAL.USUARIO.getApellidos());
        RequestQueue getinfo;
        getinfo = Volley.newRequestQueue(PedidoInfoActivity.this);
        JsonObjectRequest getJson;
        String urlgetdireccion = "https://bmarkettest.000webhostapp.com/ObtenerinfoDadoProducto/optDireccion.php?PedID="
                + PedID;
        getJson = new JsonObjectRequest(Request.Method.GET, urlgetdireccion, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try
                {
                    JSONArray jsonArray = response.getJSONArray("direccionjson");
                    for (int i = 0; i< jsonArray.length(); i++)
                    {
                        JSONObject direct = jsonArray.getJSONObject(i);
                        String direccionj = direct.getString("direccion");
                        String ciudadj = direct.getString("ciudad");
                        String estadoj = direct.getString("estado");
                        int cpj = direct.getInt("cp");
                        int totalpricej = direct.getInt("TotalPrice");
                        String fechaj = direct.getString("Fecha");
                        direccion.setText(direccionj+" "+ciudadj+" "+estadoj);
                        cp.setText(""+cpj);
                        fecha.setText(fechaj);
                        pedidoTotal.setText("$"+totalpricej);
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
getinfo.add(getJson);





        RequestQueue getQueue;
        getQueue = Volley.newRequestQueue(PedidoInfoActivity.this);
        JsonObjectRequest getJsons;
        String geturl = "https://bmarkettest.000webhostapp.com/ObtenerinfoDadoProducto/getDescripcion_Pedido.php?PedID="+this.PedID;

        getJsons = new JsonObjectRequest(Request.Method.GET, geturl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("pedidojson");

                    sizes = jsonArray.length();

                    gohea();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        getQueue.add(getJsons);






    }

    private void gohea()
    {
        Adaptador_PedidoInfo api = new Adaptador_PedidoInfo();
        api.context = this;
        api.indx = indice;
        api.PedID = PedID;
        api.sizes = sizes;
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        ListaPedidoInfo.setLayoutManager(llm);
        ListaPedidoInfo.setAdapter(api);
    }


    public void Atras(View view) {
        onBackPressed();
    }

    public void cancelarPedido(){
      /*  GLOBAL.PEDIDO.remove(indice);

        Intent intent = new Intent(this, VerPedidosActivity.class);
        intent.putExtra("car",true);
        startActivity(intent);
        finish();*/
    }
}
