package com.example.blackmarket.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.blackmarket.DireccionesActivity;
import com.example.blackmarket.GLOBAL;
import com.example.blackmarket.R;

public class Adaptador_Direcciones extends RecyclerView.Adapter<Adaptador_Direcciones.DireccionesItemActivity> {
    public Context context;
    public OnMenuItemListener listener;

    public interface OnMenuItemListener{
        void borrarDireccion(int indice);
        void editarDireccion(int i);
        void click();
    }

    public Adaptador_Direcciones(OnMenuItemListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public DireccionesItemActivity onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = View.inflate(context, R.layout.item_direccion,null);
        DireccionesItemActivity direccionesItemActivity = new DireccionesItemActivity(v);
        return direccionesItemActivity;
    }

    @Override
    public void onBindViewHolder(@NonNull DireccionesItemActivity direccionesItemActivity, int i) {
        final int indice = i;

        direccionesItemActivity.direccion.setText(GLOBAL.DIRECCIONS.get(indice).getDireccion());
        direccionesItemActivity.cp.setText(GLOBAL.DIRECCIONS.get(indice).getCp());
        direccionesItemActivity.estado.setText(GLOBAL.DIRECCIONS.get(indice).getEstado());
        direccionesItemActivity.ciudad.setText(GLOBAL.DIRECCIONS.get(indice).getCiudad());

        direccionesItemActivity.direccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GLOBAL.DIRECCIONS.get(indice).setSeleccionada(true);
                if(GLOBAL.INDICES.getDireccionSeleccionada() != -1){
                    GLOBAL.DIRECCIONS.get(GLOBAL.INDICES.getDireccionSeleccionada()).setSeleccionada(false);
                }
                GLOBAL.INDICES.setDireccionSeleccionada(indice);
                listener.click();
            }
        });

        if(GLOBAL.DIRECCIONS.get(indice).getSeleccionada()){
            direccionesItemActivity.indicador.setVisibility(View.VISIBLE);
        }else{
            direccionesItemActivity.indicador.setVisibility(View.GONE);
            if(indice == 0 && GLOBAL.INDICES.getDireccionSeleccionada()==0){
                direccionesItemActivity.indicador.setVisibility(View.VISIBLE);
            }
        }

        final PopupMenu popupMenu = new PopupMenu(context, direccionesItemActivity.menu);

        popupMenu.inflate(R.menu.direcciones_opc);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.direcciones_borrar:

                        //borrarDireccion(indice);
                        listener.borrarDireccion(indice);
                        break;
                    case R.id.direcciones_editar:
                        listener.editarDireccion(indice);
                        break;
                }
                return false;
            }
        });

        direccionesItemActivity.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });
    }



    @Override
    public int getItemCount() {
        return GLOBAL.DIRECCIONS.size();
    }

    public class DireccionesItemActivity extends RecyclerView.ViewHolder {
        public TextView ciudad, estado, cp, direccion, menu;
        public View indicador;
        public DireccionesItemActivity(@NonNull View itemView) {
            super(itemView);

            ciudad = itemView.findViewById(R.id.direcciones_ciudad);
            estado = itemView.findViewById(R.id.direcciones_estado);
            cp =  itemView.findViewById(R.id.direcciones_cp);
            direccion =  itemView.findViewById(R.id.direcciones_direccion);
            indicador = itemView.findViewById(R.id.direccion_seleccionada_indicador);

            menu = itemView.findViewById(R.id.direcciones_item_menu);
        }

    }
}
