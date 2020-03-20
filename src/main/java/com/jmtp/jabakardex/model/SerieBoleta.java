package com.jmtp.jabakardex.model;

import com.mongodb.lang.Nullable;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "serie_boleta")
public class SerieBoleta extends AbstractDocument {

    private String value;
    @Nullable
    private String nota;

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
}
