package com.example.blackmarket.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.blackmarket.GLOBAL;
import com.example.blackmarket.POJOS.Producto;
import com.example.blackmarket.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

public class Adaptador_PedidoInfo extends RecyclerView.Adapter<Adaptador_PedidoInfo.Pedidoinfominiactivity> {
    public Context context;
    public int indx;
    public int PedID;
    public int sizes;
    int size=0;
    @NonNull
    @Override
    public Pedidoinfominiactivity onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = View.inflate(context, R.layout.item_pedidoux, null);
        Pedidoinfominiactivity pedidoinfominiactivity = new Pedidoinfominiactivity(v);
        return pedidoinfominiactivity;
    }

    @Override
    public void onBindViewHolder(@NonNull final Pedidoinfominiactivity pedidoinfominiactivity, int i) {
        final int indice = i;
        RequestQueue getQueue;
        getQueue = Volley.newRequestQueue(context);
        JsonObjectRequest getJson;
        String geturl = "https://bmarkettest.000webhostapp.com/ObtenerinfoDadoProducto/getDescripcion_Pedido.php?PedID="+this.PedID;

        getJson = new JsonObjectRequest(Request.Method.GET, geturl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("pedidojson");
                    for (int i = 0; i< jsonArray.length(); i++) {
                    JSONObject pedinfj = jsonArray.getJSONObject(size);
                    int PedID = pedinfj.getInt("PedID");
                    String itemName = pedinfj.getString("itemName");
                    int itemPrice = pedinfj.getInt("itemPrice");
                    String itemImage = pedinfj.getString("itemImage");
                    int Quant = pedinfj.getInt("Quant");
                    int subtotal = pedinfj.getInt("subtotal");
                        Picasso.get()
                                .load(itemImage)
                                .into(pedidoinfominiactivity.pedidoimagen);

                   pedidoinfominiactivity.pedidonombre.setText(itemName);
                   pedidoinfominiactivity.pedidoprecio.setText("$"+itemPrice);
                    pedidoinfominiactivity.pedidocant.setText(""+Quant);

                    }
                    size++;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        getQueue.add(getJson);
/*
        pedidoinfominiactivity.pedidonombre.setText(GLOBAL.PEDIDO.get(indx).Pedido_productos.get(indice).getNombre());
        pedidoinfominiactivity.pedidocant.setText(""+GLOBAL.PEDIDO.get(indx).Pedido_productos2.get(indice).getCantidad());
        pedidoinfominiactivity.pedidoprecio.setText("$"+GLOBAL.PEDIDO.get(indx).Pedido_productos.get(indice).getPrecio());
        Picasso.get()
                .load(GLOBAL.PEDIDO.get(indx).Pedido_productos.get(indice).getImagen())
                .into(pedidoinfominiactivity.pedidoimagen);*/
    }

    @Override
    public int getItemCount() {
        return sizes;
    }

    public class Pedidoinfominiactivity extends RecyclerView.ViewHolder {
        TextView pedidonombre, pedidocant, pedidoprecio, pedidototal;
        ImageView pedidoimagen;
        public Pedidoinfominiactivity(@NonNull View itemView) {
            super(itemView);
            pedidonombre = itemView.findViewById(R.id.item_pedido_nombre);
            pedidocant = itemView.findViewById(R.id.item_pedido_cantidad);
            pedidoprecio = itemView.findViewById(R.id.item_pedido_precio);
            pedidoimagen = itemView.findViewById(R.id.item_pedido_imagen);
        }
    }
}
