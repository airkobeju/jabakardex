package com.jmtp.jabakardex.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.DBRef;

public abstract class ItemBoletaDetail extends AbstractDocument{

    private int cantidad;
    private double peso;
    private String nota;
    @DBRef private List<ItemTipoJaba> tipoJaba = new ArrayList<>();

    protected ItemBoletaDetail(){

    }
    protected ItemBoletaDetail(int cantidad, double peso){
        this.cantidad=cantidad;
        this.peso=peso;
    }
    protected ItemBoletaDetail(int cantidad, double peso, List<ItemTipoJaba> tipoJaba) {
        this.cantidad = cantidad;
        this.peso = peso;
        this.tipoJaba = tipoJaba;
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


    public void setTipoJaba(List<ItemTipoJaba> tipo){
        this.tipoJaba = tipo;
    }
    public List<ItemTipoJaba> getTipoJaba(){
        return this.tipoJaba;
    }

    @JsonIgnore
    public int tipoJabaLenght(){
        int i = 0;

        for (ItemTipoJaba obj: this.tipoJaba){
            i += obj.getCantidad();
        }
        System.out.println(i);
        return i;
    }

    @Override
    @JsonIgnoreProperties
    public String toString() {
        return "ItemBoletaDetail{" +
                "cantidad=" + cantidad +
                ", peso=" + peso +
                ", nota='" + nota + '\'' +
                ", tipoJaba=" + tipoJaba +
                ", id='" + id + '\'' +
                '}';
    }
}
