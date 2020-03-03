package com.jmtp.jabakardex.utils;

public class ItemKardexTipoJabaWrapper {

    private String idItemKardexDetail;
    private int cantidad;
    private String abreviacion;

    public ItemKardexTipoJabaWrapper() {
    }

    public ItemKardexTipoJabaWrapper(String idItemKardexDetail, int cantidad, String abreviacion) {
        this.idItemKardexDetail = idItemKardexDetail;
        this.cantidad = cantidad;
        this.abreviacion = abreviacion;
    }

    public String getIdItemKardexDetail() {
        return idItemKardexDetail;
    }

    public void setIdItemKardexDetail(String idItemKardexDetail) {
        this.idItemKardexDetail = idItemKardexDetail;
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
