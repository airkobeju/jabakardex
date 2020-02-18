package com.jmtp.jabakardex.utils;

public class AddPesoWrapper{
    private String idKardex, idItem;
    public AddPesoWrapper(){

    }
    public String getIdKardex(){
        return this.idKardex;
    }
    public void setIdKardex(String id){
        this.idKardex = id;
    }
    public String getIdItem(){
        return this.idItem;
    }
    public void setIdItem(String id){
        this.idItem = id;
    }
}