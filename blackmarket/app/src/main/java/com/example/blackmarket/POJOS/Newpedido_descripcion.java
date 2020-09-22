package com.example.blackmarket.POJOS;

public class Newpedido_descripcion
{
    public int getPedID() {
        return PedID;
    }

    public void setPedID(int pedID) {
        PedID = pedID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getQuant() {
        return Quant;
    }

    public void setQuant(int quant) {
        Quant = quant;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    int PedID;
    int itemID;
    int Quant;
    int subtotal;
}
