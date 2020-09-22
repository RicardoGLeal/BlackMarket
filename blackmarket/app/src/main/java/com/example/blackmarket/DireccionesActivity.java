package com.example.blackmarket;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.blackmarket.Adapters.Adaptador_Direcciones;
import com.example.blackmarket.POJOS.Direccion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import es.dmoral.toasty.Toasty;

public class DireccionesActivity extends AppCompatActivity implements NewDireccionDialog.NewDireccionDialogListener, EditDirectionDialog.CambiarDireccionDialogListener {

    RecyclerView listaDirecciones;
    public Adaptador_Direcciones adaptador_direcciones;
    int indice;
    boolean x,carrito;


    ProgressDialog progreso;
    ProgressDialog progreso_eliminando;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direcciones);
        indice=0;
        x = false;


        carrito = getIntent().getBooleanExtra("carrito",false);

        listaDirecciones = findViewById(R.id.Lista_direcciones);

        //getDirecciones();
        adaptador_direcciones = new Adaptador_Direcciones(new Adaptador_Direcciones.OnMenuItemListener() {


            @Override
            public void borrarDireccion(int indx) {
                deleteDireccion(indx);
                //adaptador_direcciones.notifyDataSetChanged();
            }

            @Override
            public void editarDireccion(int i) {
                x = GLOBAL.DIRECCIONS.get(i).getSeleccionada();
                indice = i;
                openDialog2();

            }

            @Override
            public void click() {
                adaptador_direcciones.notifyDataSetChanged();
            }
        });

        adaptador_direcciones.context = this;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        listaDirecciones.setLayoutManager(linearLayoutManager);
        listaDirecciones.setAdapter(adaptador_direcciones);

    }

    private void openDialog2() {
        EditDirectionDialog editDirectionDialog = new EditDirectionDialog();
        editDirectionDialog.show(getSupportFragmentManager(),"Editar direccion");
    }

    public void Atras(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if(!carrito){
            super.onBackPressed();
        }else{
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("abrirCarrito",true);
            startActivity(intent);
            finish();
        }
    }

    public void NuevaDireccion(View view) {
        openDialog();
    }

    private void openDialog() {

        NewDireccionDialog newDireccionDialog = new NewDireccionDialog();
        newDireccionDialog.show(getSupportFragmentManager(),"Nueva direccion");
    }

    @Override
    public void agregarNuevaDireccion(String cp, String direccion, String ciudad, String estado, Boolean agregar){
        RequestQueue addrequest;
        JsonObjectRequest addjsonObjectRequest;
        if(agregar){
            addrequest = Volley.newRequestQueue(DireccionesActivity.this);
            String server_url = "https://bmarkettest.000webhostapp.com/setDireccion.php?userid="
                    +GLOBAL.USUARIO.getId()
                    +"&direccion="+direccion
                    +"&ciudad="+ciudad
                    +"&estado="+estado
                    +"&cp="+cp;

            server_url.replace(" ","%20");
            addjsonObjectRequest = new JsonObjectRequest(Request.Method.GET, server_url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    progreso.hide();
                    Toast toast = Toasty.success(DireccionesActivity.this,"Se ha registrado correctamente",Toast.LENGTH_SHORT,true);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progreso.hide();
                    Toast toast = Toasty.success(DireccionesActivity.this,"Se ha registrado correctamente",Toast.LENGTH_SHORT,true);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            });
            addrequest.add(addjsonObjectRequest);
            if(GLOBAL.DIRECCIONS.isEmpty()){
                getDirecciones();
            }
            getDirecciones();
            adaptador_direcciones.notifyDataSetChanged();
        }
    }
    public void deleteDireccion(int indx){
        RequestQueue deleteQueue;
        deleteQueue = Volley.newRequestQueue(DireccionesActivity.this);
        JsonObjectRequest deleteJson;
     /*   progreso_eliminando = new ProgressDialog(this);
        progreso_eliminando.setMessage("Eliminando...");
        progreso_eliminando.show();
        */
        String deldirurl = "https://bmarkettest.000webhostapp.com/borrarDireccion.php?userid="
                +GLOBAL.USUARIO.getId()
                +"&dirid="+GLOBAL.DIRECCIONS.get(indx).getId();
        deldirurl.replace(" ", "%20");

        deleteJson = new JsonObjectRequest(Request.Method.GET, deldirurl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progreso.hide();
                Toast toast = Toasty.success(DireccionesActivity.this,"Se ha registrado correctamente",Toast.LENGTH_SHORT,true);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progreso.hide();
                Toast toast = Toasty.success(DireccionesActivity.this,"Se ha registrado correctamente",Toast.LENGTH_SHORT,true);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        });
        deleteQueue.add(deleteJson);
        if(GLOBAL.DIRECCIONS.size() == 1 || GLOBAL.DIRECCIONS.get(indx).getId() == GLOBAL.INDICES.getDireccionSeleccionada()){
            getDirecciones();
        }
        getDirecciones();
        adaptador_direcciones.notifyDataSetChanged();
    }

    @Override
    public void cambiarDireccion(String cp, String direccion, String ciudad, String estado, Boolean cambiar) {
        RequestQueue editQueue;
        editQueue = Volley.newRequestQueue(DireccionesActivity.this);
        JsonObjectRequest editJson;
        if (cambiar) {
            String uptdirurl = "https://bmarkettest.000webhostapp.com/updateDireccion.php?dirid="

                    + GLOBAL.DIRECCIONS.get(indice).getId()
                    + "&direccion=" + direccion
                    + "&ciudad=" + ciudad
                    + "&estado=" + estado
                    + "&cp=" + cp;

            uptdirurl.replace(" ", "%20");

            editJson = new JsonObjectRequest(Request.Method.GET, uptdirurl, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    progreso.hide();
                    Toast toast = Toasty.success(DireccionesActivity.this,"Se ha registrado correctamente",Toast.LENGTH_SHORT,true);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progreso.hide();
                    Toast toast = Toasty.success(DireccionesActivity.this,"Se ha registrado correctamente",Toast.LENGTH_SHORT,true);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            });
            editQueue.add(editJson);
            if(GLOBAL.DIRECCIONS.size() == 1 || GLOBAL.DIRECCIONS.get(indice).getId() == GLOBAL.INDICES.getDireccionSeleccionada()){
                getDirecciones();
            }
            getDirecciones();
            adaptador_direcciones.notifyDataSetChanged();
        }
    }

    public void getDirecciones()
    {
        progreso = new ProgressDialog(this);
        progreso.setMessage("Cargando...");
        progreso.show();
        RequestQueue getqueue;
        getqueue = Volley.newRequestQueue(DireccionesActivity.this);
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
                        GLOBAL.DIRECCIONS.add(nuevDireccion);
                        adaptador_direcciones.notifyDataSetChanged();
                        progreso.hide();
                    }

                    adaptador_direcciones.notifyDataSetChanged();
                    progreso.hide();

                } catch (JSONException e) {
                    e.printStackTrace();
                    progreso.hide();
                    GLOBAL.DIRECCIONS.clear();
                    adaptador_direcciones.notifyDataSetChanged();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progreso.hide();
                GLOBAL.DIRECCIONS.clear();
                adaptador_direcciones.notifyDataSetChanged();

            }
        });
        getqueue.add(getjson);
    }
}