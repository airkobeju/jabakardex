package com.jmtp.jabakardex.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "item_kardex_detail")
@JsonIgnoreProperties(value = { "target" })
public class ItemKardexDetail extends AbstractDocument{

    private int cantidad;
    private double peso;
    private String nota;
    @DBRef(lazy = true)
    private List<TipoJaba> tipoJaba = new ArrayList<>();

    public ItemKardexDetail(){

    }

    public ItemKardexDetail(int cantidad, double peso){
        this.cantidad=cantidad;
        this.peso=peso;
    }

    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    public int getCantidad(){
        return this.cantidad;
    }

    public void setPeso(double peso){
        this.peso = peso;
    }
    public double getPeso(){
        return this.peso;
    }

    public void setNota(String nota){
        this.nota = nota;
    }
    public String getNota(){
        return this.nota;
    }


    public void setTipoJaba(List<TipoJaba> tipo){
        this.tipoJaba = tipo;
    }
    public List<TipoJaba> getTipoJaba(){
        return this.tipoJaba;
    }

}
