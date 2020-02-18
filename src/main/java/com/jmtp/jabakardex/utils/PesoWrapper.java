package com.jmtp.jabakardex.utils;

public class PesoWrapper{

    private Integer cantidad;
    private Double peso;

    public PesoWrapper(){}
    public PesoWrapper(Integer cantidad, Double peso){
        this.cantidad = cantidad;
        this.peso = peso;
    }

    public Integer getCantidad(){
        return this.cantidad;
    }
    public void setCantidad(Integer cnt){
        this.cantidad=cnt;
    }

    public Double getPeso(){
        return this.peso;
    }
    public void setPeso(Double peso){
        this.peso=peso;
    }

}