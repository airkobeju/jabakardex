package com.jmtp.jabakardex.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "item_tipo_jaba")
public class ItemTipoJaba extends AbstractDocument{
    
    private int cantidad;
    @DBRef
    private TipoJabaMatriz tipoJaba;

    public ItemTipoJaba(){
        super();
    }

    public ItemTipoJaba(int cantidad, TipoJabaMatriz tipoJaba) {
        this.cantidad = cantidad;
        this.tipoJaba = tipoJaba;
    }

    public int getCantidad(){
        return this.cantidad;
    }
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }

    public TipoJabaMatriz getTipoJaba(){
        return this.tipoJaba;
    }
    public void setTipoJaba(TipoJabaMatriz tipojaba){
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