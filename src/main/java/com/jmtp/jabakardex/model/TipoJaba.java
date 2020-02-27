package com.jmtp.jabakardex.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "tipo_jaba")
@JsonIgnoreProperties(value = { "target" })
public class TipoJaba extends AbstractDocument{
    
    private int cantidad;
    @DBRef(lazy = true)
    private TipoJabaMatriz tipoJaba;

    public TipoJaba(){
        super();
    }

    public TipoJaba(int cantidad, TipoJabaMatriz tipoJaba) {
        this.cantidad = cantidad;
        this.tipoJaba = tipoJaba;
    }

    public int getCantidad(){
        return this.cantidad;
    }
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }

    public TipoJabaMatriz getTipoJabaMatriz(){
        return this.tipoJaba;
    }
    public void setTipoJabaMatriz(TipoJabaMatriz tipojaba){
        this.tipoJaba = tipojaba;
    }

    @Override
    public String toString() {
        return "{" +
                "\"cantidad\": " + cantidad +
                ",\"tipoJaba\": " + tipoJaba +
                '}';
    }
}