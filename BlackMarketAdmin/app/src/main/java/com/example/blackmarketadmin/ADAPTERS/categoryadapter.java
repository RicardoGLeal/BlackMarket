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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.blackmarketadmin.GLOBAL;
import com.example.blackmarketadmin.R;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import es.dmoral.toasty.Toasty;

public class categoryadapter extends RecyclerView.Adapter<categoryadapter.Minicategory> {
    public Context context;
    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private final OnItemClickListener listener;
    public interface OnItemClickListener
    { void botonpresionado();}
    public categoryadapter(OnItemClickListener listener){this.listener = listener;}
    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    ProgressDialog progreso;
    Dialog myDialog;
    @NonNull
    @Override
    public categoryadapter.Minicategory onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = View.inflate(context, R.layout.category_item, null);
      Minicategory minicategory = new Minicategory(v);
        return minicategory;
    }

    @Override
    public void onBindViewHolder(@NonNull final categoryadapter.Minicategory minicategory, int i) {
    final int indice = i;
    minicategory.name.setText(GLOBAL.CATEGORIAS.get(indice).getNombre());
    myDialog = new Dialog(context);
    myDialog.setContentView(R.layout.category_edit);


    minicategory.edit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final EditText name;
            name = myDialog.findViewById(R.id.newnamecateID);
            Button save = myDialog.findViewById(R.id.savecateID);
            name.setText(GLOBAL.CATEGORIAS.get(indice).getNombre());
            //Toast.makeText(context,"Text Click"+String.valueOf(minicategory.getAdapterPosition()),Toast.LENGTH_SHORT).show();
            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myDialog.show();

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    /*
                    minicategory.newname = name.getText().toString();
                    GLOBAL.CATEGORIAS.get(indice).setNombre(minicategory.newname);*/


                                if(name.getText().toString().isEmpty())
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
                                    String urluptCat = "https://bmarkettest.000webhostapp.com/updateCategoria.php?cateID="
                                            + GLOBAL.CATEGORIAS.get(indice).getId()
                                            + "&cateName=" + name.getText().toString();
                                    JsonObjectRequest uptcatrequest = new JsonObjectRequest(Request.Method.GET, urluptCat, null, new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            progreso.hide();
                                            Toast toast = Toasty.success(context,"Categoría actualizada correctamente",Toast.LENGTH_SHORT,true);
                                            toast.setGravity(Gravity.CENTER,0,0);
                                            toast.show();
                                            notifyDataSetChanged();
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            progreso.hide();
                                            Toast toast = Toasty.success(context,"Categoría actualizada correctamente",Toast.LENGTH_SHORT,true);
                                            toast.setGravity(Gravity.CENTER,0,0);
                                            toast.show();
                                        }
                                    });
                                        uptCatQueue.add(uptcatrequest);
                                    notifyDataSetChanged();
                                }
                                                /*
                    String qname = name.getText().toString();
                    String qimage = image.getText().toString();
                    String qdesc = desc.getText().toString();
                    int qprecio = Integer.parseInt(price.getText().toString());
                    int qcate = Integer.parseInt(category.getText().toString());
                    GLOBAL.PRODUCTOS.get(indice).setNombre(qname);
                    GLOBAL.PRODUCTOS.get(indice).setPrecio(qprecio);
                    GLOBAL.PRODUCTOS.get(indice).setCategoria(qcate);
                    GLOBAL.PRODUCTOS.get(indice).setImagen(qimage);

                    GLOBAL.PRODUCTOS.get(indice).setDescripcion(qdesc);*/
                                listener.botonpresionado();
                                myDialog.dismiss();
                            }
                        });
                    }
            });

    minicategory.delete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RequestQueue deleteQueue;
            deleteQueue= Volley.newRequestQueue(context);
            String url = "https://bmarkettest.000webhostapp.com/deleteCategoria.php?cateID="+GLOBAL.CATEGORIAS.get(indice).getId();
            JsonObjectRequest deleteRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast toast = Toasty.success(context,"Categoría eliminada correctamente",Toast.LENGTH_SHORT,true);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                    notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Toast toast = Toasty.success(context,"Categoría eliminada correctamente",Toast.LENGTH_SHORT,true);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
            }) ;
            deleteQueue.add(deleteRequest);
            notifyDataSetChanged();
            listener.botonpresionado();
        }
    });
    }


    @Override
    public int getItemCount() {
        return GLOBAL.CATEGORIAS.size();
    }

    public class Minicategory extends RecyclerView.ViewHolder {
        TextView name;
        ImageView edit, delete;
        String newname;
        public Minicategory(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.category_nameID);
            edit = itemView.findViewById(R.id.category_editID);
            delete = itemView.findViewById(R.id.category_deleteID);

        }
    }
}

