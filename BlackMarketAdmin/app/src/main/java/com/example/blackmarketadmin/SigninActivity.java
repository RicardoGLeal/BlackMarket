package com.example.blackmarketadmin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import es.dmoral.toasty.Toasty;

public class SigninActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener
{
    EditText correo, contraseña;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;
    ProgressDialog progreso;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        correo = findViewById(R.id.IDEmail);
        contraseña = findViewById(R.id.IDPassword);
        request = Volley.newRequestQueue(this);
    }

    public void LogIn(View view) {

        progreso = new ProgressDialog(com.example.blackmarketadmin.SigninActivity.this);
        progreso.setMessage("Iniciando sesion...");
        progreso.show();
        String server_url = "https://bmarkettest.000webhostapp.com/getAdmin.php?correo="+correo.getText().toString();
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, server_url, null , this, this);
        request.add(jsonObjectRequest);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast toast = Toasty.error(this,"Error al consultar...",Toast.LENGTH_SHORT,true);
        toast.setGravity(Gravity.CENTER,0,0);
    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        Verificar(response);

    }

    public void Verificar(JSONObject response){

        JSONArray json = response.optJSONArray("usuario");
        JSONObject jsonObject = null;
        boolean iniciar = false;
        try {
            jsonObject = json.getJSONObject(0);

            //String contraseña = jsonObject.optString("password")
            String correo = jsonObject.optString("correo");
            String realpass = jsonObject.optString("password");

            if(realpass.equals(contraseña.getText().toString())){
                iniciar = true;
            }
            if(json.isNull(0)){
                iniciar = false;
                Toast toast = Toasty.error(this,"Correo no asociado a ninguna cuenta...",Toast.LENGTH_SHORT,true);
                toast.setGravity(Gravity.BOTTOM,0,0);
                toast.show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(iniciar){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{

            Toast toast = Toasty.error(this,"Correo o contraseña invalido...",Toast.LENGTH_SHORT,true);
            toast.setGravity(Gravity.BOTTOM,0,0);
            toast.show();
        }
    }
}
