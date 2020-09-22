package com.example.blackmarketadmin.ADAPTERS;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
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
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import javax.microedition.khronos.opengles.GL;

import es.dmoral.toasty.Toasty;

public class Itemadapter extends RecyclerView.Adapter<Itemadapter.Miniitem> {
    public Context context;
    Dialog myDialog;
    ProgressDialog progreso;

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private final OnItemClickListener listener;
    public interface OnItemClickListener
    {void botonpresionado();}
    public Itemadapter(OnItemClickListener listener){this.listener = listener;}

    @NonNull
    @Override
    public Itemadapter.Miniitem onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = View.inflate(context, R.layout.item_product, null);
        Miniitem miniitem = new Miniitem(v);
        return miniitem;
    }

    @Override
    public void onBindViewHolder(@NonNull final Itemadapter.Miniitem miniitem, int i) {
        final int indice = i;
        miniitem.name.setText(GLOBAL.PRODUCTOS.get(indice).getNombre());
        miniitem.precio.setText("$"+GLOBAL.PRODUCTOS.get(indice).getPrecio());
        Picasso.get()
                .load(GLOBAL.PRODUCTOS.get(indice).getImagen())
                .into(miniitem.image);


        miniitem.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue deleteQueue;
                deleteQueue= Volley.newRequestQueue(context);
                String url = "https://bmarkettest.000webhostapp.com/deleteItem.php?itemID="+GLOBAL.PRODUCTOS.get(indice).getItemID();
                JsonObjectRequest deleteRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast toast = Toasty.success(context,"Item eliminado satisfactoriamente",Toast.LENGTH_SHORT,true);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.show();
                        notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast toast = Toasty.success(context,"Item eliminado satisfactoriamente",Toast.LENGTH_SHORT,true);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.show();
                    }
                }) ;
                deleteQueue.add(deleteRequest);
                notifyDataSetChanged();
                //GLOBAL.PRODUCTOS.remove(indice);
                listener.botonpresionado();
            }
        });

        miniitem.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog = new Dialog(context);
                myDialog.setContentView(R.layout.item_dialog);
                final EditText name, price, category, image, desc;
                final Button save;
                name = myDialog.findViewById(R.id.itemDialog_nameID);
                price = myDialog.findViewById(R.id.itemDialog_priceID);
                category = myDialog.findViewById(R.id.itemDialog_cateID);
                image = myDialog.findViewById(R.id.itemDialog_imageID);
                desc = myDialog.findViewById(R.id.itemDialog_descID);
                save = myDialog.findViewById(R.id.itemDialog_saveID);

                name.setText(GLOBAL.PRODUCTOS.get(indice).getNombre());
                price.setText(""+GLOBAL.PRODUCTOS.get(indice).getPrecio());
                category.setText(""+GLOBAL.PRODUCTOS.get(indice).getCategoria());
                image.setText(GLOBAL.PRODUCTOS.get(indice).getImagen());
                desc.setText(GLOBAL.PRODUCTOS.get(indice).getDescripcion());
                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
                        if(name.getText().toString().isEmpty() || desc.getText().toString().isEmpty() || category.getText().toString().isEmpty() || price.getText().toString().isEmpty() || image.getText().toString().isEmpty()){
                            Toast toast = Toasty.error(context,"Llena todos los datos",Toast.LENGTH_SHORT,true);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();
                        }else
                            {
                            progreso = new ProgressDialog(context);
                            progreso.setMessage("Cargando...");
                            progreso.show();
                            RequestQueue insertQueue;
                            insertQueue = Volley.newRequestQueue(context);
                            String urli = "https://bmarkettest.000webhostapp.com/updateItem.php?itemName="
                                    + name.getText().toString()
                                    + "&itemDesc=" + desc.getText().toString()
                                    + "&itemCat=" + category.getText().toString()
                                    + "&itemPrice=" + price.getText().toString()
                                    + "&itemImage=" + image.getText().toString()
                                    + "&itemID=" + GLOBAL.PRODUCTOS.get(indice).getItemID();
                            urli.replace(" ", "%20");
                            JsonObjectRequest insertrequest = new JsonObjectRequest(Request.Method.GET, urli, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    progreso.hide();
                                    Toast toast = Toasty.success(context,"Item actualizado correctamente",Toast.LENGTH_SHORT,true);
                                    toast.setGravity(Gravity.CENTER,0,0);
                                    toast.show();
                                    notifyDataSetChanged();
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progreso.hide();
                                    Toast toast = Toasty.success(context,"Item actualizado correctamente",Toast.LENGTH_SHORT,true);
                                    toast.setGravity(Gravity.CENTER,0,0);
                                    toast.show();
                                }
                            });
                            insertQueue.add(insertrequest);
                            notifyDataSetChanged();
                        }
                    listener.botonpresionado();
                    myDialog.dismiss();
                    }
                });


            }
        });
    }

    @Override
    public int getItemCount() {
        return GLOBAL.PRODUCTOS.size();
    }

    public class Miniitem extends RecyclerView.ViewHolder {
        TextView name, precio;
        ImageView image, edit, delete;
        public Miniitem(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_nameID);
            edit = itemView.findViewById(R.id.item_editID);
            delete = itemView.findViewById(R.id.item_deleteID);
            image = itemView.findViewById(R.id.item_product_imagen);
            precio = itemView.findViewById(R.id.item_product_precioID);

        }
    }
}
