package com.jmtp.jabakardex.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mongodb.lang.Nullable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "kardex_serie")
@JsonIgnoreProperties(value = { "target" })
public class KardexSerie extends AbstractDocument {


    private String value;
    @Nullable
    private String note;

    public KardexSerie() {
    }

    public KardexSerie(String value) {
        this.value = value;
    }

    public KardexSerie(String value, String note) {
        this.value = value;
        this.note = note;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
