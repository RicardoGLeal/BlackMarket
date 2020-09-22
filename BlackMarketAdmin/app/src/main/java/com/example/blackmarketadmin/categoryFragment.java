package com.example.blackmarketadmin;
import android.app.Dialog;
import android.content.Context;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.blackmarketadmin.ADAPTERS.categoryadapter;
import com.example.blackmarketadmin.POJOS.Categoria;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import es.dmoral.toasty.Toasty;

public class categoryFragment extends Fragment /*implements Response.ErrorListener implements Response.Listener<JSONObject>,Response.ErrorListener*/{
    View v;
    RecyclerView listaCategorias;
    categoryadapter CATE;
    Dialog addDialog;
    ImageView add;
    Button save;
    EditText name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_universal, container, false);
        listaCategorias = v.findViewById(R.id.Lista_categorias);
        add = v.findViewById(R.id.addID);
        addDialog = new Dialog(getContext());
        addDialog.setContentView(R.layout.category_edit);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                addDialog.show();
                save = addDialog.findViewById(R.id.savecateID);
                name = addDialog.findViewById(R.id.newnamecateID);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String qname = name.getText().toString();
                        InsertCate();
                        addDialog.dismiss();
                        CATE.notifyDataSetChanged();
                    }
                });
            }
        });

    getCategories();
        CATE = new categoryadapter(new categoryadapter.OnItemClickListener() {
            @Override
            public void botonpresionado() {
                CATE.notifyDataSetChanged();
            }
        });
        CATE.context = getContext();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        listaCategorias.setAdapter(CATE);
        listaCategorias.setLayoutManager(linearLayoutManager);
        return v;
    }

    public void InsertCate()
    {
        if(name.getText().toString().isEmpty()){
            Toast toast = Toasty.error(getContext(),"Llena todos los datos",Toast.LENGTH_SHORT,true);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }else {
            RequestQueue insertQueue;
            insertQueue = Volley.newRequestQueue(getContext());
            String urli = "https://bmarkettest.000webhostapp.com/addCategory.php?cateName="
                    + name.getText().toString();
            JsonObjectRequest insertrequest = new JsonObjectRequest(Request.Method.GET, urli, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getContext(), "Se ha registrado correctamente", Toast.LENGTH_SHORT).show();
                    CATE.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "No se pudo registrar :c", Toast.LENGTH_SHORT).show();
                }
            });
            insertQueue.add(insertrequest);
            CATE.notifyDataSetChanged();
        }
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
                            }
                            CATE.notifyDataSetChanged();
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

}
