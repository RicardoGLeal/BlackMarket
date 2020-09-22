package com.example.blackmarket.POJOS;

public class Newpedido {
    int PedID;

    public int getPedID() {
        return PedID;
    }

    public void setPedID(int pedID) {
        PedID = pedID;
    }

    public int getDicID() {
        return DicID;
    }

    public void setDicID(int dicID) {
        DicID = dicID;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCP() {
        return CP;
    }

    public void setCP(int CP) {
        this.CP = CP;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    int DicID;
    String direccion;
    int CP;
    String fecha;
    int status;
}
