package com.example.blackmarket;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.provider.Settings;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blackmarket.POJOS.Carrito;
import com.example.blackmarket.POJOS.Pedido;
import com.example.blackmarket.POJOS.Producto;
import com.example.blackmarket.POJOS.Producto_Cant;
import com.example.blackmarket.POJOS.Usuario;
import com.squareup.picasso.Picasso;

import es.dmoral.toasty.Toasty;

public class ProductInfoActivity extends AppCompatActivity {

    int indice;
    int x;
    Producto_Cant productoCant;
    ImageView imagen;
    TextView nombre;
    TextView precio;
    TextView desc;
    RatingBar ratingBar;
    TextView ratingText;
    Button menosCant, masCant;

    EditText cantidad;
    Button addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        x = getIntent().getIntExtra("indice",-1);

        indice = obtenerIndex(x);

        imagen = findViewById(R.id.image_product_info);
        nombre = findViewById(R.id.nombre_product_info);
        precio = findViewById(R.id.precio_product_info);
        desc = findViewById(R.id.desc_product_info);
        ratingBar = findViewById(R.id.product_valoracion_stars);
        ratingText = findViewById(R.id.product_valoracion_text);
        menosCant = findViewById(R.id.menos_cantidad);
        masCant = findViewById(R.id.mas_cantidad);
        cantidad = findViewById(R.id.procuct_cantidad);
        addButton = findViewById(R.id.comprar_product);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                añadirAlCarrito();
            }
        });
        menosCant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menos();
            }
        });
        masCant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mas();
            }
        });
        LlenarDatos();
    }

    private void añadirAlCarrito() {
        int n = Integer.parseInt(cantidad.getText().toString());

        if(n == 0){
            Toast toast = Toasty.error(this,"La cantidad no puede ser 0",Toast.LENGTH_SHORT,true);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }else{
            if(!GLOBAL.PRODUCTOS.get(indice).isCarrito()){
                productoCant = new Producto_Cant();

                Toast toast = Toasty.success(this,"Agregado al carrito!",Toast.LENGTH_SHORT,true);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();

                GLOBAL.CARRITO.addCarrito_productos(GLOBAL.PRODUCTOS.get(indice));

               // productoCant.setProductID(GLOBAL.PRODUCTOS.get(indice));
                productoCant.setCantidad(n);

                GLOBAL.CARRITO.addCarrito_productos2(productoCant);

                GLOBAL.PRODUCTOS.get(indice).setCarrito(true);

            }else{

                Toast toast = Toasty.success(this,"Ya se agrego al carrito!",Toast.LENGTH_SHORT,true);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        }
    }

    private void menos() {
        int n = Integer.parseInt(cantidad.getText().toString());
        if(n >= 2){
            n--;
        }
        cantidad.setText(""+n);
        //GLOBAL.PRODUCTOS.get(indice).setCantidad(n);
    }
    private void mas() {
        int n = Integer.parseInt(cantidad.getText().toString());
        n++;
        cantidad.setText(""+n);
        //GLOBAL.PRODUCTOS.get(indice).setCantidad(n);
    }

    public void LlenarDatos(){
        Picasso.get().load(GLOBAL.PRODUCTOS.get(indice).getImagen()).into(imagen);

        nombre.setText(GLOBAL.PRODUCTOS.get(indice).getNombre());
        precio.setText("$" +GLOBAL.PRODUCTOS.get(indice).getPrecio());
        desc.setText(GLOBAL.PRODUCTOS.get(indice).getDescripcion());

        ratingBar.setRating(GLOBAL.PRODUCTOS.get(indice).getRating());

        if(GLOBAL.PRODUCTOS.get(indice).getRating()>=4.5){
            ratingText.setText("Excelente!");
        }else if(GLOBAL.PRODUCTOS.get(indice).getRating()<=2){
            ratingText.setText("Malo");
        }else{
            ratingText.setText("Bueno");
        }

        cantidad.setText("1");
    }

    public void Atras(View view) {
        onBackPressed();
    }

    public void AbrirCarrito(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("abrirCarrito",true);
        startActivity(intent);
        finish();
    }
    public int obtenerIndex(int idProduct){
        int inx = 0;

        for(int i = 0; i<GLOBAL.PRODUCTOS.size(); i++){
            if(idProduct == GLOBAL.PRODUCTOS.get(i).getItemID()){
                inx = GLOBAL.PRODUCTOS.indexOf(GLOBAL.PRODUCTOS.get(i));
                return inx;
            }
        }

        return inx;
    }
}
