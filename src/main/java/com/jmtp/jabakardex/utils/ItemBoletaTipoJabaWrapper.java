package com.jmtp.jabakardex.utils;

public class ItemBoletaTipoJabaWrapper {

    private String idItemBoletal;
    private int cantidad;
    private String abreviacion;

    public ItemBoletaTipoJabaWrapper() {
    }

    public ItemBoletaTipoJabaWrapper(String idItemBoletal, int cantidad, String abreviacion) {
        this.idItemBoletal = idItemBoletal;
        this.cantidad = cantidad;
        this.abreviacion = abreviacion;
    }

    public String getIdItemBoletal() {
        return idItemBoletal;
    }

    public void setIdItemBoletal(String idItemBoletal) {
        this.idItemBoletal = idItemBoletal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getAbreviacion() {
        return abreviacion;
    }

    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
    }
}
