package com.example.blackmarket;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class CuentaFragment extends Fragment {

    View v;

    TextView cerrarSesion, verPedidos, perfil, direcciones;
    TextView nombre, correo, apellidos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_cuenta, container, false);
        cerrarSesion = v.findViewById(R.id.cuenta_conf_cerrar_sesion);
        verPedidos = v.findViewById(R.id.cuenta_ver_predidos);
        perfil = v.findViewById(R.id.cuenta_conf_perfil);
        direcciones = v.findViewById(R.id.cuenta_direcciones);

        nombre = v.findViewById(R.id.cuenta_perfil_nombre);
        apellidos = v.findViewById(R.id.cuenta_perfil_apellidos);
        correo = v.findViewById(R.id.cuenta_perfil_correo);

        nombre.setText(GLOBAL.USUARIO.getNombre());
        apellidos.setText(GLOBAL.USUARIO.getApellidos());
        correo.setText(GLOBAL.USUARIO.getCorreo());

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CerrarSesion();
            }
        });

        verPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerPedidos();
            }
        });

        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Perfil();
            }
        });

        direcciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Direcciones();
            }
        });

        return v;
    }

    private void Perfil() {
        Intent intent = new Intent(getContext(),ConfPerfilActivity.class);
        startActivity(intent);
    }

    private void VerPedidos() {
        Intent intent = new Intent(getContext(),VerPedidosActivity.class);
        startActivity(intent);
    }

    private void CerrarSesion() {
        GLOBAL.CARRITO.carrito_productos.clear();
        GLOBAL.CARRITO.carrito_productos2.clear();

        Intent intent = new Intent(getContext(),SigninActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void Direcciones(){
        Intent intent = new Intent(getContext(),DireccionesActivity.class);
        startActivity(intent);
    }
}
