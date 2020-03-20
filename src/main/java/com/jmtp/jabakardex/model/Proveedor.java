package com.jmtp.jabakardex.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "proveedor")
public class Proveedor extends AbstractDocument{

    @Indexed(unique = true)
    private String name;
    private String firstName;
    private String lastName;

    public Proveedor(){
        super();
    }

    public Proveedor(String name, String firstName, String lastName) {
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
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