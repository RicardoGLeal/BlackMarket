package com.example.blackmarket;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class NewDireccionDialog extends AppCompatDialogFragment {

    private EditText cp, direccion, ciudad, estado;
    private NewDireccionDialogListener listener;

    public interface NewDireccionDialogListener{
        void agregarNuevaDireccion(String cp, String direccion, String ciudad, String estado, Boolean agregar);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_new_direccion,null);

        cp = view.findViewById(R.id.dialog_cp);
        direccion = view.findViewById(R.id.dialog_direccion);
        ciudad = view.findViewById(R.id.dialog_ciudad);
        estado = view.findViewById(R.id.dialog_estado);

        builder.setView(view)
                .setTitle("Nueva direccion")
                .setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String _cp = cp.getText().toString();
                        String _direccion = direccion.getText().toString();
                        String _ciudad = ciudad.getText().toString();
                        String _estado = estado.getText().toString();
                        Boolean agregar;

                        if(_cp.isEmpty() || _ciudad.isEmpty() || _direccion.isEmpty() || _estado.isEmpty()){
                            Toast toast = Toasty.error(getContext(),"Llena todos los campos!",Toast.LENGTH_SHORT,true);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();

                            agregar = false;
                        }else{
                            agregar = true;
                        }

                        listener.agregarNuevaDireccion(_cp,_direccion,_ciudad,_estado,agregar);
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            listener = (NewDireccionDialogListener) context;
        }catch (ClassCastException e){
            throw  new ClassCastException(context.toString() +"must implement DialogListener");
        }
    }


}
