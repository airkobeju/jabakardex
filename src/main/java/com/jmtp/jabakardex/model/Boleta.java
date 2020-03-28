package com.jmtp.jabakardex.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "boleta")
public class Boleta extends AbstractDocument {

    @DBRef private SerieBoleta serie;
    private Long numeracion;
    @JsonFormat(pattern = "yyyy-MM-dd") private LocalDate fecha;
    @DBRef private Proveedor proveedor;
    private String nota;
    @DBRef private List<ItemsEntrada> itemsEntrada = new ArrayList<>();
    @DBRef private List<ItemsSalida> itemsSalida = new ArrayList<>();
    private boolean venta = false;
    private boolean isClose = false;

    public Boleta() {
        super();
    }
    public Boleta(LocalDate fecha, Proveedor proveedor, String nota) {
        this.fecha=fecha;
        this.proveedor=proveedor;
        this.nota=nota;
    }
    public Boleta(LocalDate fecha, Proveedor proveedor) {
        this.fecha=fecha;
        this.proveedor=proveedor;
    }
    public Boleta(SerieBoleta serie, Long numeracion, LocalDate fecha, Proveedor proveedor, String nota) {
        this.serie = serie;
        this.numeracion = numeracion;
        this.fecha = fecha;
        this.proveedor = proveedor;
        this.nota = nota;
    }

    public SerieBoleta getSerie() {
        return serie;
    }
    public void setSerie(SerieBoleta serie) {
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

    public void setItemsSalida(List<ItemsSalida> itemsSalida){
        this.itemsSalida = itemsSalida;
    }
    public List<ItemsSalida> getItemsSalida(){
        return this.itemsSalida;
    }

    public List<ItemsEntrada> getItemsEntrada() {
        return itemsEntrada;
    }
    public void setItemsEntrada(List<ItemsEntrada> itemsEntrada) {
        this.itemsEntrada = itemsEntrada;
    }

    public boolean isVenta() {
        return venta;
    }
    public void setVenta(boolean venta) {
        this.venta = venta;
    }

    public boolean isClose() {
        return isClose;
    }
    public void setClose(boolean close) {
        isClose = close;
    }

    @Override
    @JsonIgnoreProperties
    public String toString() {
        return "Boleta{" +
                "serie=" + serie +
                ", numeracion=" + numeracion +
                ", fecha=" + fecha +
                ", proveedor=" + proveedor +
                ", itemsEntrada=" + itemsEntrada +
                ", itemsSalida=" + itemsSalida +
                ", venta=" + venta +
                ", isClose=" + isClose +
                ", id='" + id + '\'' +
                '}';
    }
}
