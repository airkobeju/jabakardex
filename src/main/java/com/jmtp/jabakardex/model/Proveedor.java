package com.jmtp.jabakardex.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "proveedor")
@JsonIgnoreProperties(value = { "target" })
public class Proveedor extends AbstractDocument{

    @Indexed(unique = true)
    private String name;
    private String firstName;
    private String lastName;

    public Proveedor(){

    }

    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }

    public void setFirstName(String name){
        this.firstName=name;
    }
    public String getFirstName(){
        return this.firstName;
    }

    public void setLastName(String name){
        this.lastName=name;
    }
    public String getLastName(){
        return this.lastName;
    }

}