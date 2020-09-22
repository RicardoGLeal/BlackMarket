package com.example.blackmarket.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.blackmarket.GLOBAL;
import com.example.blackmarket.PedidoInfoActivity;
import com.example.blackmarket.R;

public class Adaptador_Pedidos extends RecyclerView.Adapter<Adaptador_Pedidos.Pedidosminiactivity> {
  public Context context;

    @NonNull
    @Override
    public Pedidosminiactivity onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = View.inflate(context,R.layout.item_pedido,null);
        Pedidosminiactivity pedidosminiactivity = new Pedidosminiactivity(v);
        return pedidosminiactivity;
    }

    @Override
    public void onBindViewHolder(@NonNull Pedidosminiactivity pedidosminiactivity, int i)
    {
        final int indice = i;
        pedidosminiactivity.Direccion.setText(GLOBAL.NEWPEDIDOS.get(indice).getDireccion());
        pedidosminiactivity.cp.setText(""+ GLOBAL.NEWPEDIDOS.get(indice).getCP());
        pedidosminiactivity.fecha.setText(GLOBAL.NEWPEDIDOS.get(indice).getFecha());
      /*  pedidosminiactivity.Direccion.setText(GLOBAL.DIRECCIONS.get(GLOBAL.PEDIDO.get(indice).getIdx_dir()).getDireccion());
        pedidosminiactivity.cp.setText(GLOBAL.DIRECCIONS.get(GLOBAL.PEDIDO.get(indice).getIdx_dir()).getCp());
        pedidosminiactivity.fecha.setText(GLOBAL.PEDIDO.get(indice).getFecha());*/
        pedidosminiactivity.Direccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PedidoInfo = new Intent(context, PedidoInfoActivity.class);
                PedidoInfo.putExtra("indice", indice);
                PedidoInfo.putExtra("PedID", GLOBAL.NEWPEDIDOS.get(indice).getPedID());

                context.startActivity(PedidoInfo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return GLOBAL.NEWPEDIDOS.size();
    }

    public class Pedidosminiactivity extends RecyclerView.ViewHolder {
        TextView Direccion, cp, fecha;
        public Pedidosminiactivity(@NonNull View itemView) {
            super(itemView);
            Direccion = itemView.findViewById(R.id.item_pedido_direccion);
            cp = itemView.findViewById(R.id.item_pedido_cp);
            fecha = itemView.findViewById(R.id.fecha_pedido_item);
        }
    }
}
