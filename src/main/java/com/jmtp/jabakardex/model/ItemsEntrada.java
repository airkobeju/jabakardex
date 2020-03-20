package com.jmtp.jabakardex.model;

import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "items_entrada")
public class ItemsEntrada extends ItemBoletaDetail {

    public ItemsEntrada() {
    }

    public ItemsEntrada(int cantidad, double peso) {
        super(cantidad, peso);
    }

    public ItemsEntrada(int cantidad, double peso, List<ItemTipoJaba> tipoJaba) {
        super(cantidad, peso, tipoJaba);
    }
}
