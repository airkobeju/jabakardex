package com.jmtp.jabakardex.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "proveedor")
public class Proveedor extends AbstractDocument{

    @Indexed(unique = true)
    private String name;
    private String firstName;
    private String lastName;
    @DBRef
    private TipoJabaMatriz tipoJaba;

    public Proveedor(){
        super();
    }

    public Proveedor(String name, String firstName, String lastName) {
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Proveedor(String name, String firstName, String lastName, TipoJabaMatriz tipoJaba) {
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tipoJaba = tipoJaba;
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

    public TipoJabaMatriz getTipoJaba() {
        return tipoJaba;
    }
    public void setTipoJaba(TipoJabaMatriz tipoJaba) {
        this.tipoJaba = tipoJaba;
    }

    @Override
    @JsonIgnoreProperties
    public String toString() {
        return "Proveedor{" +
                "name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}