package com.example.blackmarket;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import es.dmoral.toasty.Toasty;

public class ConfPerfilActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener {

    ImageButton atras;
    Button guardar;
    EditText nombre, apellidos, password, correo;
    Toolbar toolbar;
    TextView textocontra;
    LinearLayout contraContainer;
    boolean edit;

    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf_perfil);
        atras = findViewById(R.id.back_conf);

        edit = false;
        request = Volley.newRequestQueue(this);

        guardar = findViewById(R.id.boton_modificar_perfil);

        toolbar = findViewById(R.id.toolbar_conf);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        nombre = findViewById(R.id.nombre_perfil);
        apellidos = findViewById(R.id.apellidos_pefil);
        password = findViewById(R.id.nueva_password_perfil);
        correo = findViewById(R.id.correo_perfil);
        textocontra = findViewById(R.id.texto_contra);
        contraContainer = findViewById(R.id.contra_container);

        nombre.setText(GLOBAL.USUARIO.getNombre());
        apellidos.setText(GLOBAL.USUARIO.getApellidos());
        correo.setText(GLOBAL.USUARIO.getCorreo());


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuardarUsuario();
                edit = true;
            }
        });

    }

    private void GuardarUsuario() {

        progreso = new ProgressDialog(this);
        progreso.setMessage("Cargando...");
        progreso.show();

        if(!correo.getText().equals("")){
            GLOBAL.USUARIO.setCorreo(correo.getText().toString());
        }
        if(!password.getText().equals("")){
            GLOBAL.USUARIO.setContraseña(password.getText().toString());
        }
        if(!nombre.getText().equals("")){
            GLOBAL.USUARIO.setNombre(nombre.getText().toString());
        }
        if(!apellidos.getText().equals("")){
            GLOBAL.USUARIO.setApellidos(apellidos.getText().toString());
        }

        String server_url = "https://bmarkettest.000webhostapp.com/updateUser.php?nombre="
                +GLOBAL.USUARIO.getNombre()
                +"&apellidos="+GLOBAL.USUARIO.getApellidos()
                +"&contrasena="+GLOBAL.USUARIO.getContraseña()
                +"&correo="+GLOBAL.USUARIO.getCorreo()
                +"&userid="+GLOBAL.USUARIO.getId();

        server_url.replace(" ","%20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, server_url,null,this,this);
        request.add(jsonObjectRequest);

        guardar.setVisibility(View.GONE);

        nombre.setFocusableInTouchMode(false);
        nombre.setFocusable(false);
        nombre.setClickable(false);

        apellidos.setFocusableInTouchMode(false);
        apellidos.setFocusable(false);
        apellidos.setClickable(false);

        correo.setFocusableInTouchMode(false);
        correo.setFocusable(false);
        correo.setClickable(false);

        textocontra.setVisibility(View.INVISIBLE);
        contraContainer.setVisibility(View.INVISIBLE);

    }

    public void Atras(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if(edit){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("abrirCarrito",false);
            startActivity(intent);
            finish();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.perfil_opc,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.perfil_editar:

                guardar.setVisibility(View.VISIBLE);

                nombre.setFocusableInTouchMode(true);
                nombre.setFocusable(true);
                nombre.setClickable(true);

                apellidos.setFocusableInTouchMode(true);
                apellidos.setFocusable(true);
                apellidos.setClickable(true);

                correo.setFocusableInTouchMode(true);
                correo.setFocusable(true);
                correo.setClickable(true);

                textocontra.setVisibility(View.VISIBLE);
                contraContainer.setVisibility(View.VISIBLE);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast toast = Toasty.success(this,"Guardado!",Toast.LENGTH_SHORT,true);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    @Override
    public void onResponse(JSONObject response) {
        progreso.hide();
        Toast toast = Toasty.success(this,"Guardado!",Toast.LENGTH_SHORT,true);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
}
