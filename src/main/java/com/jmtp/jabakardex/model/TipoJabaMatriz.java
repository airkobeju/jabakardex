package com.jmtp.jabakardex.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tipo_jaba_matriz")
public class TipoJabaMatriz extends AbstractDocument{
    
    public String name, abreviacion;


    public TipoJabaMatriz(){

    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setAbreviacion(String abreviacion){
        this.abreviacion = abreviacion;
    }
    public String getAbreviacion(){
        return this.abreviacion;
    }
}