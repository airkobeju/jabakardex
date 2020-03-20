package com.jmtp.jabakardex.utils;

import java.util.ArrayList;
import java.util.List;

public class PesosWrapper {
 
    private String idBoleta;
    private List<PesoWrapper> pesos = new ArrayList<>();

    public PesosWrapper(){}
    public PesosWrapper(List<PesoWrapper> items){
        this.pesos=items;
    }
    public PesosWrapper(String idBoleta, List<PesoWrapper> items){
        this.idBoleta = idBoleta;
        this.pesos=items;
    }

    public String getIdBoleta(){
        return this.idBoleta;
    }

    public void setIdBoleta(String id){
        this.idBoleta = id;
    }

    public void setItems(List<PesoWrapper> items){
        this.pesos.clear();
        this.pesos.addAll(items);
    }

    public void addItem(PesoWrapper item){
        this.pesos.add(item);
    }

    public List<PesoWrapper> getPesos(){
        return this.pesos;
    }

    public PesoWrapper getPeso(int index){
        return this.pesos.get(index);
    }


}