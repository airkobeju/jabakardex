package com.jmtp.jabakardex.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mongodb.lang.Nullable;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "serie_boleta")
public class SerieBoleta extends AbstractDocument {

    private String value;
    @Nullable
    private String nota;
    @DBRef private TipoOperacion operacion;

    public SerieBoleta() {
        super();
    }

    public SerieBoleta(String value) {
        this.value = value;
    }

    public SerieBoleta(String value, String nota) {
        this.value = value;
        this.nota = nota;
    }

    public SerieBoleta(String value, TipoOperacion operacion) {
        this.value = value;
        this.operacion = operacion;
    }

    public SerieBoleta(String value, @Nullable String nota, TipoOperacion operacion) {
        this.value = value;
        this.nota = nota;
        this.operacion = operacion;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public TipoOperacion getOperacion() {
        return operacion;
    }

    public void setOperacion(TipoOperacion operacion) {
        this.operacion = operacion;
    }

    @Override
    @JsonIgnoreProperties
    public String toString() {
        return "SerieBoleta{" +
                "value='" + value + '\'' +
                ", nota='" + nota + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
