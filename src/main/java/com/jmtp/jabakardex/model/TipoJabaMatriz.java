package com.jmtp.jabakardex.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tipo_jaba_matriz")
@JsonIgnoreProperties(value = { "target" })
public class TipoJabaMatriz extends AbstractDocument{
    
    public String name, abreviacion;

    public TipoJabaMatriz(){
        super();
    }

    public TipoJabaMatriz(String name, String abreviacion) {
        this.name = name;
        this.abreviacion = abreviacion;
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