package com.example.blackmarketadmin;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.blackmarketadmin.ADAPTERS.usersadapter;
import com.example.blackmarketadmin.POJOS.Usuarios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import es.dmoral.toasty.Toasty;

public class usersFragment extends Fragment
{
    View v;
    RecyclerView Userlist;
    ImageView add;
    TextView type;
    Dialog addDialog;
    usersadapter USERA;
    ProgressDialog progreso;

    EditText name, lastname, phone, email, password;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_universal, container, false);
        Userlist = v.findViewById(R.id.Lista_categorias);
        add = v.findViewById(R.id.addID);
        type = v.findViewById(R.id.tipo);
        type.setText("Usuarios:");
        addDialog = new Dialog(getContext());
        addDialog.setContentView(R.layout.user_dialog);
        //------------------------------------------------------------NEW USER--------------------------------------------------------------------------------------------//
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button save;
                name = addDialog.findViewById(R.id.userDialog_nameID);
                lastname = addDialog.findViewById(R.id.userDialog_lastnameID);
                phone = addDialog.findViewById(R.id.userDialog_phoneID);
                email = addDialog.findViewById(R.id.userDialog_emailID);
                password = addDialog.findViewById(R.id.userDialog_passwordID);
                save = addDialog.findViewById(R.id.userDialog_saveID);
                addDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                addDialog.show();

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*
                        String qname = name.getText().toString();
                        String qlastname = lastname.getText().toString();
                        String qphone =phone.getText().toString();
                        String qemail = email.getText().toString();
                        String qpassword = password.getText().toString();

                        Usuarios usuario = new Usuarios();
                        usuario.setName(qname);
                        usuario.setLastname(qlastname);
                        usuario.setPhone(qphone);
                        usuario.setEmail(qemail);
                        usuario.setPassword(qpassword);

                        GLOBAL.USUARIOS.add(usuario);*/
                        InsertUser();
                        addDialog.dismiss();
                        USERA.notifyDataSetChanged();
                    }
                });


            }
        });
        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------//
        getUsers();
        USERA = new usersadapter(new usersadapter.OnItemClickListener() {
            @Override
            public void botonpresionado() {
                USERA.notifyDataSetChanged();
            }
        });
        USERA.context=getContext();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        Userlist.setAdapter(USERA);
        Userlist.setLayoutManager(linearLayoutManager);
        return v;
    }

    public void InsertUser() {
        if(name.getText().toString().isEmpty() || lastname.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
            Toast toast = Toasty.error(getContext(),"Llena todos los datos",Toast.LENGTH_SHORT,true);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }else {
            progreso = new ProgressDialog(getContext());
            progreso.setMessage("Cargando...");
            progreso.show();

            String server_url = "https://bmarkettest.000webhostapp.com/setUser.php?nombre="
                    + name.getText().toString()
                    + "&apellidos=" + lastname.getText().toString()
                    + "&contrasena=" + password.getText().toString()
                    + "&telefono=" + phone.getText().toString()
                    + "&correo=" + email.getText().toString();

            server_url.replace(" ", "%20");
            RequestQueue insertQueue;
            insertQueue = Volley.newRequestQueue(getContext());
            JsonObjectRequest insert = new JsonObjectRequest(Request.Method.GET, server_url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(getContext(),"Usuario registrado correctamente",Toast.LENGTH_SHORT).show();
                    USERA.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progreso.hide();
                    Toast toast = Toasty.success(getContext(),"Usuario registrado correctamente",Toast.LENGTH_SHORT,true);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            });
            insertQueue.add(insert);
            USERA.notifyDataSetChanged();
        }
    }

    public void getUsers() {
        RequestQueue usersQueue;
        usersQueue = Volley.newRequestQueue(getContext());
        String url = "https://bmarkettest.000webhostapp.com/getusers.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("usersjson");
                    GLOBAL.USUARIOS.clear();
                    for (int i = 0; i<jsonArray.length(); i++) {
                        JSONObject userj = jsonArray.getJSONObject(i);
                        int userid = userj.getInt("id");
                        String username = userj.getString("nombre");
                        String userlastname = userj.getString("apellidos");
                        String userphone = userj.getString("telefono");
                        String useremail = userj.getString("correo");
                        String userpassword = userj.getString("password");
                        Usuarios user = new Usuarios();
                        user.setId(userid);
                        user.setName(username);
                        user.setLastname(userlastname);
                        user.setPhone(userphone);
                        user.setEmail(useremail);
                        user.setPassword(userpassword);
                        GLOBAL.USUARIOS.add(user);
                    }
                    USERA.notifyDataSetChanged();
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
        usersQueue.add(request);
    }

}
