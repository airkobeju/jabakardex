package com.jmtp.jabakardex.utils;

import com.jmtp.jabakardex.model.ItemsSalida;

import java.util.ArrayList;
import java.util.List;

public class VentaPesosWrapper {

    private String idBoleta;
    private List<ItemsSalida> pesos = new ArrayList<>();

    public VentaPesosWrapper() {
        super();
    }

    public VentaPesosWrapper(String idBoleta, List<ItemsSalida> pesos) {
        this.idBoleta = idBoleta;
        this.pesos = pesos;
    }

    public String getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(String idBoleta) {
        this.idBoleta = idBoleta;
    }

    public List<ItemsSalida> getPesos() {
        return pesos;
    }

    public void setPesos(List<ItemsSalida> pesos) {
        this.pesos = pesos;
    }
}
