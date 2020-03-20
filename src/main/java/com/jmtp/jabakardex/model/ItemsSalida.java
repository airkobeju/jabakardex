package com.jmtp.jabakardex.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "items_salida")
public class ItemsSalida extends ItemBoletaDetail {

    public ItemsSalida() {
        super();
    }

    public ItemsSalida(int cantidad, double peso) {
        super(cantidad, peso);
    }

    public ItemsSalida(int cantidad, double peso, List<ItemTipoJaba> tipoJaba) {
        super(cantidad, peso, tipoJaba);
    }

}
