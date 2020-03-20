package com.jmtp.jabakardex.utils;

import com.jmtp.jabakardex.model.ItemsEntrada;

import java.util.ArrayList;
import java.util.List;

public class CompraPesosWrapper {

    private String idBoleta;
    private List<ItemsEntrada> pesos = new ArrayList<>();

    public CompraPesosWrapper() {
        super();
    }
    public CompraPesosWrapper(String idBoleta, List<ItemsEntrada> pesos) {
        this.idBoleta = idBoleta;
        this.pesos = pesos;
    }

    public String getIdBoleta() {
        return idBoleta;
    }
    public void setIdBoleta(String idBoleta) {
        this.idBoleta = idBoleta;
    }

    public List<ItemsEntrada> getPesos() {
        return pesos;
    }
    public void setPesos(List<ItemsEntrada> pesos) {
        this.pesos = pesos;
    }

}
