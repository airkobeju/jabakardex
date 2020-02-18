package com.jmtp.jabakardex.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "tipo_jaba")
public class TipoJaba{
    
    public int cantidad;
    @DBRef()
    public TipoJabaMatriz tipoJaba;

    public TipoJaba(){

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

}