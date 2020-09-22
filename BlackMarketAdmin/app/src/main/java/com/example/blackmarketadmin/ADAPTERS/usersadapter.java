package com.example.blackmarketadmin.ADAPTERS;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
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
import com.example.blackmarketadmin.GLOBAL;
import com.example.blackmarketadmin.R;

import org.json.JSONObject;

import es.dmoral.toasty.Toasty;

public class usersadapter extends RecyclerView.Adapter<usersadapter.Miniusers> {
    public Context context;
    ProgressDialog progreso;

    Dialog myDialog;
    private final OnItemClickListener listener;
    public interface OnItemClickListener
    {
        void botonpresionado();
    }
    public usersadapter(OnItemClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public usersadapter.Miniusers onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
    View v = View.inflate(context,R.layout.category_item,null);
    Miniusers miniusers = new Miniusers(v);
        return miniusers;
    }

    @Override
    public void onBindViewHolder(@NonNull final usersadapter.Miniusers miniusers, int i) {
        final int indice = i;
        miniusers.name.setText(GLOBAL.USUARIOS.get(indice).getName());
        miniusers.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            myDialog = new Dialog(context);
            myDialog.setContentView(R.layout.user_dialog);
                final EditText name, lastname, phone, email, password;
                Button save;
                name = myDialog.findViewById(R.id.userDialog_nameID);
                lastname = myDialog.findViewById(R.id.userDialog_lastnameID);
                phone = myDialog.findViewById(R.id.userDialog_phoneID);
                email = myDialog.findViewById(R.id.userDialog_emailID);
                password = myDialog.findViewById(R.id.userDialog_passwordID);
                save = myDialog.findViewById(R.id.userDialog_saveID);

                name.setText(GLOBAL.USUARIOS.get(indice).getName());
                lastname.setText(GLOBAL.USUARIOS.get(indice).getLastname());
                phone.setText(""+GLOBAL.USUARIOS.get(indice).getPhone());
               email.setText(GLOBAL.USUARIOS.get(indice).getEmail());
                password.setText(GLOBAL.USUARIOS.get(indice).getPassword());
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                   /*    String qname = name.getText().toString();
                        String qlastname = lastname.getText().toString();
                        String qphone = phone.getText().toString();
                        String qemail = email.getText().toString();
                        String qpassword = password.getText().toString();

                        GLOBAL.USUARIOS.get(indice).setName(qname);
                        GLOBAL.USUARIOS.get(indice).setLastname(qlastname);
                        GLOBAL.USUARIOS.get(indice).setPhone(qphone);
                        GLOBAL.USUARIOS.get(indice).setEmail(qemail);
                        GLOBAL.USUARIOS.get(indice).setPassword(qpassword);*/
                        if(name.getText().toString().isEmpty() || lastname.getText().toString().isEmpty() || password.getText().toString().isEmpty()|| email.getText().toString().isEmpty())
                        {
                            Toast toast = Toasty.error(context,"Llena todos los datos",Toast.LENGTH_SHORT,true);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();
                        }
                        else
                        {
                            progreso = new ProgressDialog(context);
                            progreso.setMessage("Cargando...");
                            progreso.show();
                            RequestQueue uptCatQueue;
                            uptCatQueue = Volley.newRequestQueue(context);
                            String urluptCat = "https://bmarkettest.000webhostapp.com/updateUser.php?nombre="
                                    + name.getText().toString()
                                    + "&apellidos=" + lastname.getText().toString()
                                    + "&contrasena=" + password.getText().toString()
                                    + "&correo=" + email.getText().toString()
                                    + "&userid=" + GLOBAL.USUARIOS.get(indice).getId();
                            JsonObjectRequest uptcatrequest = new JsonObjectRequest(Request.Method.GET, urluptCat, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    progreso.hide();
                                    Toast toast = Toasty.success(context,"Usuario actualizado correctamente",Toast.LENGTH_SHORT,true);
                                    toast.setGravity(Gravity.CENTER,0,0);
                                    toast.show();
                                    notifyDataSetChanged();
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progreso.hide();
                                    Toast toast = Toasty.success(context,"Usuario actualizado correctamente",Toast.LENGTH_SHORT,true);
                                    toast.setGravity(Gravity.CENTER,0,0);
                                    toast.show();
                                    notifyDataSetChanged();
                                }
                            });
                            uptCatQueue.add(uptcatrequest);
                            notifyDataSetChanged();
                        }
                        listener.botonpresionado();
                        myDialog.dismiss();
                    }
                });
            }
        });

        miniusers.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue deleteQueue;
                deleteQueue= Volley.newRequestQueue(context);
                String url = "https://bmarkettest.000webhostapp.com/deleteUser.php?userid="+GLOBAL.USUARIOS.get(indice).getId();
                JsonObjectRequest deleteRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast toast = Toasty.success(context,"Usuario eliminado correctamente",Toast.LENGTH_SHORT,true);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.show();
                        notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast toast = Toasty.success(context,"Usuario eliminado correctamente",Toast.LENGTH_SHORT,true);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.show();
                        notifyDataSetChanged();
                    }
                }) ;
                deleteQueue.add(deleteRequest);
                notifyDataSetChanged();
                listener.botonpresionado();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return GLOBAL.USUARIOS.size();
    }

    public class Miniusers extends RecyclerView.ViewHolder {
        TextView name;
        ImageView edit, delete;
        public Miniusers(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.category_nameID);
            edit = itemView.findViewById(R.id.category_editID);
            delete = itemView.findViewById(R.id.category_deleteID);
        }
    }
}
