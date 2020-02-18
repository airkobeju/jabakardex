package com.jmtp.jabakardex.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class KardexControllerModel {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    
    private String proveedorId;
    private String nota;
    
    /**
     * jabaEntregada: jabas vacias
     * jabaRecepcionada: jabas con producto
     */
    private int jabaEntregada, jabaRecepcionada;

    public KardexControllerModel(){

    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getProveedorId() {
        return this.proveedorId;
    }

    public void setProveedorId(String proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getNota() {
        return this.nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
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

}