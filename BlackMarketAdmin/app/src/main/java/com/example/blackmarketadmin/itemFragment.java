package com.example.blackmarketadmin;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.blackmarketadmin.ADAPTERS.Itemadapter;
import com.example.blackmarketadmin.POJOS.Producto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import es.dmoral.toasty.Toasty;

public class itemFragment extends Fragment {
    View v;
    RecyclerView listaCategorias;
    ImageView add;
    TextView type;
    Itemadapter IT;
    Dialog addDialog;
    EditText name, price, category, image, desc;
    ProgressDialog progreso;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_universal, container, false);
        listaCategorias = v.findViewById(R.id.Lista_categorias);
        add = v.findViewById(R.id.addID);
        type = v.findViewById(R.id.tipo);
        type.setText("Articulos:");
        addDialog = new Dialog(getContext());
        addDialog.setContentView(R.layout.item_dialog);

        //---------------------------------------------------------------ADD NEW ITEM---------------------------------------------------------------------------------------------//
add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final Button save;
        name = addDialog.findViewById(R.id.itemDialog_nameID);
        price = addDialog.findViewById(R.id.itemDialog_priceID);
        category = addDialog.findViewById(R.id.itemDialog_cateID);
        image = addDialog.findViewById(R.id.itemDialog_imageID);
        desc = addDialog.findViewById(R.id.itemDialog_descID);
        save = addDialog.findViewById(R.id.itemDialog_saveID);
        addDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        addDialog.show();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertItem();
                addDialog.dismiss();
                IT.notifyDataSetChanged();
            }
        });
    }
});
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
       getItems();
        IT = new Itemadapter(new Itemadapter.OnItemClickListener() {
            @Override
            public void botonpresionado() {
                IT.notifyDataSetChanged();
            }
        });
        IT.context = getContext();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        listaCategorias.setAdapter(IT);
        listaCategorias.setLayoutManager(linearLayoutManager);
        return v;
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
                        produ.setItemID(itemid);
                        produ.setNombre(itemname);
                        produ.setDescripcion(itemdesc);
                        produ.setCategoria(itemcat);
                        produ.setPrecio(itemprice);
                        produ.setImagen(itemimage);
                        GLOBAL.PRODUCTOS.add(produ);
                    }
                    IT.notifyDataSetChanged();
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

    public void InsertItem()
    {
        if(name.getText().toString().isEmpty() || desc.getText().toString().isEmpty() || category.getText().toString().isEmpty() || price.getText().toString().isEmpty() || image.getText().toString().isEmpty()){
            Toast toast = Toasty.error(getContext(),"Llena todos los datos",Toast.LENGTH_SHORT,true);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }else {
            progreso = new ProgressDialog(getContext());
            progreso.setMessage("Cargando...");
            progreso.show();
            RequestQueue insertQueue;
            insertQueue = Volley.newRequestQueue(getContext());
            String urli = "https://bmarkettest.000webhostapp.com/setItem.php?itemName="
                    + name.getText().toString()
                    + "&itemDesc=" + desc.getText().toString()
                    + "&itemCat=" + category.getText().toString()
                    + "&itemPrice=" + price.getText().toString()
                    + "&itemImage=" + image.getText().toString();
            urli.replace(" ", "%20");
            JsonObjectRequest insertrequest = new JsonObjectRequest(Request.Method.GET, urli, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    progreso.hide();
                    Toast toast = Toasty.success(getContext(),"Se ha registrado correctamente",Toast.LENGTH_SHORT,true);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    IT.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progreso.hide();
                    Toast toast = Toasty.success(getContext(),"Se ha registrado correctamente",Toast.LENGTH_SHORT,true);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            });
            insertQueue.add(insertrequest);
            IT.notifyDataSetChanged();
        }
    }
}
