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

public class EditDirectionDialog extends AppCompatDialogFragment {

    private EditText cp, direccion, ciudad, estado;
    private EditDirectionDialog.CambiarDireccionDialogListener listener;

    public interface CambiarDireccionDialogListener{
        void cambiarDireccion(String cp, String direccion, String ciudad, String estado, Boolean cambiar);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_edit_direction,null);

        cp = view.findViewById(R.id.dialog2_cp);
        direccion = view.findViewById(R.id.dialog2_direccion);
        ciudad = view.findViewById(R.id.dialog2_ciudad);
        estado = view.findViewById(R.id.dialog2_estado);

        builder.setView(view)
                .setTitle("Cambiar direccion")
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
                        Boolean cambiar;

                        if(_cp.isEmpty() || _ciudad.isEmpty() || _direccion.isEmpty() || _estado.isEmpty()){
                            Toast toast = Toasty.error(getContext(),"Llena todos los campos!",Toast.LENGTH_SHORT,true);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();

                            cambiar = false;
                        }else{
                            cambiar = true;
                        }

                        listener.cambiarDireccion(_cp,_direccion,_ciudad,_estado, cambiar);
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            listener = (EditDirectionDialog.CambiarDireccionDialogListener) context;
        }catch (ClassCastException e){
            throw  new ClassCastException(context.toString() +"must implement DialogListener");
        }
    }

}
