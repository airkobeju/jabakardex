package com.jmtp.jabakardex.utils;

public class KardexSerieWrapper {

    private String value;
    private String note;

    public KardexSerieWrapper() {
    }

    public KardexSerieWrapper(String value, String note) {
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
