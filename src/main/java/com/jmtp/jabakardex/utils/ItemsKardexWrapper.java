package com.jmtp.jabakardex.utils;

import java.util.ArrayList;
import java.util.List;

public class ItemsKardexWrapper{
 
    private String idKardex;
    private List<PesoWrapper> pesos = new ArrayList<>();

    public ItemsKardexWrapper(){}
    public ItemsKardexWrapper(List<PesoWrapper> items){
        this.pesos=items;
    }
    public ItemsKardexWrapper(String idKardex, List<PesoWrapper> items){
        this.idKardex=idKardex;
        this.pesos=items;
    }

    public String getIdKardex(){
        return this.idKardex;
    }

    public void setIdKardex(String id){
        this.idKardex = id;
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