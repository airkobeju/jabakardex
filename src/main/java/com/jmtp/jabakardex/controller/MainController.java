package com.jmtp.jabakardex.controller;

import com.jmtp.jabakardex.model.*;
import com.jmtp.jabakardex.repository.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class MainController{

    private SerieBoletaRepository sbRepo;
    private TipoJabaMatrizRepository tjmRepo;
    private ProveedorRepository pRepo;
    private TipoOperacionRepository opRepo;

    private ItemTipoJabaRepository itjRepo;
    private ItemsEntradaRepository entradasBoletaRepo;
    private ItemsSalidaRepository  salidasBoletaRepo;
    private BoletaRepository boletaRepo;

    public MainController(
            SerieBoletaRepository sbRepo,
            TipoJabaMatrizRepository tjmRepo,
            ProveedorRepository pRepo,
            TipoOperacionRepository opRepo,
            ItemTipoJabaRepository itjRepo,
            ItemsEntradaRepository entradasBoletaRepo,
            ItemsSalidaRepository salidasBoletaRepo,
            BoletaRepository boletaRepo) {
        this.sbRepo = sbRepo;
        this.tjmRepo = tjmRepo;
        this.pRepo = pRepo;
        this.opRepo = opRepo;
        this.itjRepo = itjRepo;
        this.entradasBoletaRepo = entradasBoletaRepo;
        this.salidasBoletaRepo = salidasBoletaRepo;
        this.boletaRepo = boletaRepo;
    }

    @GetMapping("index")
    public String index(){
        return "Hola desde JabaKardex";
    }

    @GetMapping(path={"kardex"})
    public List<Map> kardex(){
        List<Boleta> boletas = boletaRepo.findAll(
                Sort.by(Sort.Order.desc("fecha"),
                Sort.Order.desc("numeracion")));
        List<Map> lst = new ArrayList<>();

        for(Boleta b: boletas){
            Map<String,Object> item = new HashMap<>();
            item.put("id", b.getId());
            item.put("fecha", b.getFecha());
            item.put("proveedor", b.getProveedor().getName());
            item.put("serie", b.getSerie().getValue());
            item.put("numeracion", b.getNumeracion());

            List<TipoJabaMatriz> tipoJabas = tjmRepo.findAll(Sort.by(Sort.Order.asc("name")));

            for(TipoJabaMatriz tj: tipoJabas){
                item.put("e_"+tj.getName().toLowerCase(), 0);
                item.put("s_"+tj.getName().toLowerCase(), 0);
            }

            for(ItemsEntrada ie: b.getItemsEntrada()){
                Integer cantidad = 0;
                for(ItemTipoJaba itj: ie.getTipoJaba() ){
                    item.replace("e_"+itj.getTipoJaba().getName().toLowerCase(),itj.getCantidad());
                }
            }
            for(ItemsSalida ie: b.getItemsSalida()){
                Integer cantidad = 0;
                for(ItemTipoJaba itj: ie.getTipoJaba() ){
                    item.replace("s_"+itj.getTipoJaba().getName().toLowerCase(),itj.getCantidad());
                }
            }

            lst.add(item);
        }


        return lst;
    }

    @GetMapping(path={"kardex/export"})
    public ResponseEntity<InputStreamResource> exportKardex() throws Exception{
        Workbook workbook = new HSSFWorkbook();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("Kardex");
        Row row_0 = sheet.createRow(0);

        row_0.createCell(0).setCellValue("ID");
        row_0.createCell(1).setCellValue("FECHA");
        row_0.createCell(2).setCellValue("PROVEEDOR");
        row_0.createCell(3).setCellValue("SERIE");
        row_0.createCell(4).setCellValue("N°");

        List<Boleta> boletas = boletaRepo.findAll(
                Sort.by(Sort.Order.desc("fecha"),
                        Sort.Order.desc("numeracion")));
        List<TipoJabaMatriz> tipoJabas = tjmRepo.findAll(Sort.by(Sort.Order.asc("name")));

        int i = 5;
        //Entradas
        for(TipoJabaMatriz j: tipoJabas){
            row_0.createCell(i).setCellValue("E"+j.getName().toUpperCase());
            i++;
        }
        //Salidas
        for(TipoJabaMatriz j: tipoJabas){
            row_0.createCell(i).setCellValue("S"+j.getName().toUpperCase());
            i++;
        }

        i = 1;
        for(Boleta b: boletas){
            Row row = sheet.createRow(i);
            row.createCell(0).setCellValue(b.getId());
            row.createCell(1).setCellValue(b.getFecha().toString());
            row.createCell(2).setCellValue(b.getProveedor().getName());
            row.createCell(3).setCellValue(b.getSerie().getValue());
            row.createCell(4).setCellValue(b.getNumeracion().toString());

            for(int _c=5; _c < (2*tipoJabas.size())+5; _c++){
                row.createCell(_c).setCellValue(0);
            }
            //Entradas
            for(ItemsEntrada ie:b.getItemsEntrada()){
                for(ItemTipoJaba itj:ie.getTipoJaba()){
                    int col_cell = 5;
                    for(TipoJabaMatriz j: tipoJabas){
                        if( itj.getTipoJaba().getName().equalsIgnoreCase(j.getName()) ){
                            double pss = row.getCell(col_cell).getNumericCellValue();
                            row.getCell(col_cell).setCellValue(pss + itj.getCantidad());
                        }
                        col_cell++;
                    }
                }
            }

            //Salidas
            for(ItemsSalida is:b.getItemsSalida()){
                for(ItemTipoJaba itj:is.getTipoJaba()){
                    int col_cell = 5 + tipoJabas.size();
                    for(TipoJabaMatriz j: tipoJabas){
                        if( itj.getTipoJaba().getName().equalsIgnoreCase(j.getName()) ){
                            double pss = row.getCell(col_cell).getNumericCellValue();
                            row.getCell(col_cell).setCellValue(pss + itj.getCantidad());
                        }
                        col_cell++;
                    }
                }
            }

            i++;
        }

        workbook.write(stream);
        workbook.close();
        ByteArrayInputStream salida = new ByteArrayInputStream(stream.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=kardex.xls");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(salida));
    }

    @GetMapping("/initdb")
    public Boleta initDB(){
        //====================Matrices=======================
        List<TipoOperacion> operaciones = new ArrayList<>();
        operaciones.add(new TipoOperacion("COMPRA","Adquisición de producto"));
        operaciones.add(new TipoOperacion("VENTA","Transacción de salida de producto."));
        operaciones = opRepo.saveAll(operaciones);

        List<SerieBoleta> series = new ArrayList<>();
        series.add(new SerieBoleta("001","Serie interna de control", operaciones.get(0)));
        series.add(new SerieBoleta("002","Serie de compra acopio", operaciones.get(0)));
        series.add(new SerieBoleta("BC003","Serie de compra camión rojo", operaciones.get(0)));
        series.add(new SerieBoleta("BC004","Serie de compra camión blanco", operaciones.get(0)));
        series.add(new SerieBoleta("BV001","Serie de venta del acopio", operaciones.get(1)));
        series.add(new SerieBoleta("BV002","Serie de venta del camión", operaciones.get(1)));
        series = sbRepo.saveAll(series);

        List<TipoJabaMatriz> tipoJabas = new ArrayList<>();
        tipoJabas.add(new TipoJabaMatriz("Color","c",true));
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
        proveedores.add(new Proveedor("Mauricia M.","Mauricia","Mejía", tipoJabas.get(1)));
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
