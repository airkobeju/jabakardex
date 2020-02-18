package com.jmtp.jabakardex.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "kardex")
@JsonIgnoreProperties(value = { "target" })
public class Kardex extends AbstractDocument {

    @DBRef(lazy = true)
    private KardexSerie serie;
    private Long numeracion;

    private LocalDate fecha;
    @DBRef(lazy = true)
    private Proveedor proveedor;
    private String nota;
    @DBRef(lazy = true)
    private List<ItemKardexDetail> items = new ArrayList<>();
    /**
     * jabaEntregada: jabas vacias
     * jabaRecepcionada: jabas con producto
     */
    private int jabaEntregada = 0, jabaRecepcionada = 0;
    private boolean isClose = false;

    public Kardex() {

    }
    public Kardex(LocalDate fecha,Proveedor proveedor,String nota,int jabaEntregada) {
        this.fecha=fecha;
        this.proveedor=proveedor;
        this.nota=nota;
        this.jabaEntregada=jabaEntregada;
    }
    public Kardex(LocalDate fecha,Proveedor proveedor,int jabaEntregada) {
        this.fecha=fecha;
        this.proveedor=proveedor;
        this.jabaEntregada=jabaEntregada;
    }
    public Kardex(LocalDate fecha,Proveedor proveedor,String nota) {
        this.fecha=fecha;
        this.proveedor=proveedor;
        this.nota=nota;
    }
    public Kardex(LocalDate fecha,Proveedor proveedor) {
        this.fecha=fecha;
        this.proveedor=proveedor;
     
    }

    public KardexSerie getSerie() {
        return serie;
    }

    public void setSerie(KardexSerie serie) {
        this.serie = serie;
    }

    public Long getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(Long numeracion) {
        this.numeracion = numeracion;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Proveedor getProveedor() {
        return this.proveedor;
    }

    public void setProveedor(Proveedor name) {
        this.proveedor = name;
    }

    public String getNota() {
        return this.nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public void setItems(List<ItemKardexDetail> items){
        this.items = items;
    }
    public List<ItemKardexDetail> getItems(){
        return this.items;
    }

    public int getJabaEntregada() {
        return this.jabaEntregada;
    }

    public void setJabaEntregada(int entregado) {
        this.jabaEntregada = entregado;
    }

    public int getJabaRecepcionada() {
        return this.jabaRecepcionada;
    }

    public void setJabaRecepcionada(int recepcion) {
        this.jabaRecepcionada = recepcion;
    }

    public boolean isClose() {
        return isClose;
    }

    public void setClose(boolean close) {
        isClose = close;
    }
}