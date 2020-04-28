package com.jmtp.jabakardex.utils;

import com.jmtp.jabakardex.model.*;

public final class Utils {

    public static Boleta boletaChecked(Boleta boleta, TipoJabaMatriz tjDefault){
        if(boleta.isVenta()){
            for(ItemsSalida salida: boleta.getItemsSalida()){
                salida.setPeso( Math.round(salida.getPeso()*100)/100 );
                int dif = salida.getCantidad() - salida.tipoJabaLenght();
                if(dif>0){
                    salida.getTipoJaba().add(new ItemTipoJaba(dif,tjDefault));
                }
            }
        }else{
            for(ItemsEntrada entrada: boleta.getItemsEntrada()){
                entrada.setPeso( Math.round(entrada.getPeso()*100)/100 );
                int dif = entrada.getCantidad() - entrada.tipoJabaLenght();
                if(dif>0){
                    entrada.getTipoJaba().add(new ItemTipoJaba(dif,tjDefault));
                }
            }
        }

        return boleta;
    }

}
