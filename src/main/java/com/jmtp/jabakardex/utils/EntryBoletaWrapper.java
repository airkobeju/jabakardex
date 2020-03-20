package com.jmtp.jabakardex.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EntryBoletaWrapper {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    private String serieId;
    private String proveedorId;
    private List<PesoWrapper> entradas = new ArrayList<>();
    private List<PesoWrapper> salidas = new ArrayList<>();

    public EntryBoletaWrapper(){
        super();
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getSerieId() {
        return serieId;
    }

    public void setSerieId(String serieId) {
        this.serieId = serieId;
    }

    public String getProveedorId() {
        return this.proveedorId;
    }

    public void setProveedorId(String proveedorId) {
        this.proveedorId = proveedorId;
    }

    public List<PesoWrapper> getEntradas(){
        return this.entradas;
    }
    public PesoWrapper getPeso(int index){
        return this.entradas.get(index);
    }

}