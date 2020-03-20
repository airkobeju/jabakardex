package com.jmtp.jabakardex.controller;

import com.jmtp.jabakardex.model.*;
import com.jmtp.jabakardex.repository.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class MainController{

    private SerieBoletaRepository sbRepo;
    private TipoJabaMatrizRepository tjmRepo;
    private ProveedorRepository pRepo;

    private ItemTipoJabaRepository itjRepo;
    private ItemsEntradaRepository entradasBoletaRepo;
    private ItemsSalidaRepository  salidasBoletaRepo;
    private BoletaRepository boletaRepo;

    public MainController(
            SerieBoletaRepository sbRepo,
            TipoJabaMatrizRepository tjmRepo,
            ProveedorRepository pRepo,
            ItemTipoJabaRepository itjRepo,
            ItemsEntradaRepository entradasBoletaRepo,
            ItemsSalidaRepository salidasBoletaRepo,
            BoletaRepository boletaRepo) {
        this.sbRepo = sbRepo;
        this.tjmRepo = tjmRepo;
        this.pRepo = pRepo;
        this.itjRepo = itjRepo;
        this.entradasBoletaRepo = entradasBoletaRepo;
        this.salidasBoletaRepo = salidasBoletaRepo;
        this.boletaRepo = boletaRepo;
    }

    @GetMapping("index")
    public String index(){
        return "Hola desde JabaKardex";
    }

    @GetMapping("/initdb")
    public Boleta initDB(){
        //====================Matrices=======================
        List<SerieBoleta> series = new ArrayList<>();
        series.add(new SerieBoleta("001","Serie interna de control"));
        series.add(new SerieBoleta("002","Serie de compra acopio"));
        series.add(new SerieBoleta("BC003","Serie de compra camión rojo"));
        series.add(new SerieBoleta("BC004","Serie de compra camión blanco"));
        series = sbRepo.saveAll(series);

        List<TipoJabaMatriz> tipoJabas = new ArrayList<>();
        tipoJabas.add(new TipoJabaMatriz("Color","c"));
        tipoJabas.add(new TipoJabaMatriz("Danper","d"));
        tipoJabas.add(new TipoJabaMatriz("Beta","b"));
        tipoJabas.add(new TipoJabaMatriz("Viru","v"));
        tipoJabas.add(new TipoJabaMatriz("Global","g"));
        tipoJabas = tjmRepo.saveAll(tipoJabas);

        List<Proveedor> proveedores = new ArrayList<>();
        proveedores.add(new Proveedor("Juan T.","Juan Manuel","Ticona Pacheco"));
        proveedores.add(new Proveedor("Celeste V.","Celeste Elizabeth","Verdeguer Argomedo"));
        proveedores.add(new Proveedor("Mia T.","Mia Mauren","Ticona Verdeguer"));
        proveedores.add(new Proveedor("Donovan T.","Donovan Ian","Ticona Verdeguer"));
        proveedores.add(new Proveedor("Dino Q.","Dino","Quijano"));
        proveedores.add(new Proveedor("Mauricia M.","Mauricia","Mejía"));
        proveedores.add(new Proveedor("Luis M.","Luis","Morales"));
        proveedores.add(new Proveedor("Yolvi B.","Yolvi","Blas"));
        proveedores = pRepo.saveAll(proveedores);
        //============================FIN==============================

        Boleta boleta = new Boleta();
        boleta.setSerie(series.get(2));
        boleta.setNumeracion(1234l);
        boleta.setFecha(LocalDate.now());
        boleta.setProveedor(proveedores.get(1));
        boleta.setNota("Primera boleta");

        ItemsEntrada entrada1 = new ItemsEntrada(10, 172.4);
        ItemsEntrada entrada2 = new ItemsEntrada(10, 170.7);
        List<ItemTipoJaba> itemTipoJabas1 = new ArrayList<>();
        itemTipoJabas1.add(new ItemTipoJaba(8,tipoJabas.get(0)));
        itemTipoJabas1.add(new ItemTipoJaba(2,tipoJabas.get(1)));
        List<ItemTipoJaba> itemTipoJabas2 = new ArrayList<>();
        itemTipoJabas2.add(new ItemTipoJaba(5,tipoJabas.get(2)));
        itemTipoJabas2.add(new ItemTipoJaba(5,tipoJabas.get(3)));
        itemTipoJabas1 = itjRepo.saveAll(itemTipoJabas1);
        itemTipoJabas2 = itjRepo.saveAll(itemTipoJabas2);
        entrada1.setTipoJaba(itemTipoJabas1);
        entrada2.setTipoJaba(itemTipoJabas2);
        List<ItemsEntrada> entradas = new ArrayList<>();
        entradas.add(entrada1);
        entradas.add(entrada2);
        entradasBoletaRepo.saveAll(entradas);

        boleta.setItemsEntrada(entradas);

        ItemsSalida salida1 = new ItemsSalida(20, 0);
        List<ItemTipoJaba> itemTipoJabas3 = new ArrayList<>();
        itemTipoJabas3.add(new ItemTipoJaba(8,tipoJabas.get(0)));
        itemTipoJabas3.add(new ItemTipoJaba(2,tipoJabas.get(1)));
        itemTipoJabas3.add(new ItemTipoJaba(5,tipoJabas.get(2)));
        itemTipoJabas3.add(new ItemTipoJaba(5,tipoJabas.get(3)));
        itemTipoJabas3 = itjRepo.saveAll(itemTipoJabas3);
        salida1.setTipoJaba(itemTipoJabas3);
        List<ItemsSalida> salidas = new ArrayList<>();
        salidas.add(salida1);
        salidasBoletaRepo.saveAll(salidas);

        boleta.setItemsSalida(salidas);

        //System.out.println( boleta.toString() );

        boleta = boletaRepo.save(boleta);

        return boleta;
    }

}