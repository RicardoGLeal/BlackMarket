package com.example.blackmarket.POJOS;
import java.util.ArrayList;
public class Pedido {
        public  ArrayList<Producto> Pedido_productos = new ArrayList<>();
        public ArrayList<Producto_Cant> Pedido_productos2 = new ArrayList<>();
        public int id;
        public int status;
        String fecha;
        int idx_dir;
        int total = 0;


        public  ArrayList<Producto> getPedido_pruductos() {
            return Pedido_productos;
        }

        public void setPedido_productos(ArrayList<Producto> Pedido_productos) {
            this.Pedido_productos = Pedido_productos;
        }

        public void addPedido_productos(Producto Pedido_productos) {
            this.Pedido_productos.add(Pedido_productos);
        }
        public int getTotal() {
            return total;
        }

        public  void setTotal(int total){
            this.total = total;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdx_dir() {
        return idx_dir;
    }

    public void setIdx_dir(int idx_dir) {
        this.idx_dir = idx_dir;
    }
}




