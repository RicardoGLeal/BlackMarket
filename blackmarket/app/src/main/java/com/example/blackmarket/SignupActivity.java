package com.example.blackmarket;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class SignupActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener
{
    EditText nombre, apellidoP, apellidoM, telefono, correo, contraseña;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nombre = findViewById(R.id.IDName);
        apellidoP = findViewById(R.id.IDAPpaterno);
        apellidoM = findViewById(R.id.IDAPmaterno);
        telefono = findViewById(R.id.IDTel);
        correo = findViewById(R.id.IDEmail_signup);
        contraseña = findViewById(R.id.IDPassword_signup);

        request = Volley.newRequestQueue(SignupActivity.this);


    }

    public void Guardar(View view) {

       if(nombre.getText().toString().isEmpty() || apellidoM.getText().toString().isEmpty() || apellidoP.getText().toString().isEmpty() || telefono.getText().toString().isEmpty() || correo.getText().toString().isEmpty() || contraseña.getText().toString().isEmpty()){
           Toast toast = Toasty.error(this,"Llena todos los datos",Toast.LENGTH_SHORT,true);
           toast.setGravity(Gravity.CENTER,0,0);
           toast.show();
       }else{
           progreso = new ProgressDialog(this);
           progreso.setMessage("Cargando...");
           progreso.show();

           String apellidos = apellidoP.getText().toString() + " " + apellidoM.getText().toString();

           String server_url = "https://bmarkettest.000webhostapp.com/setUser.php?nombre="
                   +nombre.getText().toString()
                   +"&apellidos="+apellidos
                   +"&contrasena="+contraseña.getText().toString()
                   +"&telefono="+telefono.getText().toString()
                   +"&correo="+correo.getText().toString();

           server_url.replace(" ","%20");

           jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, server_url,null,this,this);
           request.add(jsonObjectRequest);
       }
    }

    public void Atras(View view) {
        onBackPressed();
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        progreso.hide();
        Toast toast = Toasty.success(this,"Registrado!",Toast.LENGTH_SHORT,true);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
        onBackPressed();
    }

    @Override
    public void onResponse(JSONObject response) {

        progreso.hide();
        Toast toast = Toasty.success(this,"Registrado!",Toast.LENGTH_SHORT,true);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();

        onBackPressed();
    }
}
