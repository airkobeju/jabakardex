package com.jmtp.jabakardex.model;

import java.time.LocalDate;
import java.util.HashMap;

public class KardexView {

    private LocalDate fecha;
    private Proveedor proveedor;
    private SerieBoleta serie;
    private long numeracion;
    HashMap<TipoJabaMatriz, Long> jabasEntrada;
    HashMap<TipoJabaMatriz, Long> jabasSalida;

}
