package com.jmtp.jabakardex.controller;

import java.util.HashMap;
import java.util.List;

import com.jmtp.jabakardex.model.ItemKardexDetail;
import com.jmtp.jabakardex.model.Kardex;
import com.jmtp.jabakardex.model.KardexSerie;
import com.jmtp.jabakardex.model.Proveedor;
import com.jmtp.jabakardex.repository.ItemKardexDetailRepository;
import com.jmtp.jabakardex.repository.KardexRepository;
import com.jmtp.jabakardex.repository.KardexSerieRepository;
import com.jmtp.jabakardex.repository.ProveedorRepository;
import com.jmtp.jabakardex.utils.AddPesoWrapper;
import com.jmtp.jabakardex.utils.EntryKardexWrapper;
import com.jmtp.jabakardex.utils.IdWrapper;
import com.jmtp.jabakardex.utils.ItemsKardexWrapper;
import com.jmtp.jabakardex.utils.PesoWrapper;

import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/kardex")
public class KardexController {

    private KardexRepository kardexRepository;
    private ProveedorRepository proveedorRepository;
    private ItemKardexDetailRepository ikr;
    private KardexSerieRepository ksr;

    public KardexController(
        KardexRepository kardexRepository, 
        ProveedorRepository proveedorRepository,
        ItemKardexDetailRepository ikr,
        KardexSerieRepository ksr){
        this.kardexRepository = kardexRepository;
        this.proveedorRepository = proveedorRepository;
        this.ikr = ikr;
        this.ksr = ksr;
    }

    @GetMapping("/all")
    public List<Kardex> getAll(){
        return kardexRepository.findAll(Sort.by(Sort.Direction.DESC, "fecha"));
    }

    @PostMapping("/get")
    public Kardex getKardexEntry(@RequestBody IdWrapper idwrapper){
        return kardexRepository.findById(idwrapper.getId()).get();
    }

    // @PutMapping("/save")
    // public Kardex save(@RequestBody KardexControllerModel entry){
    //     Proveedor proveedor = proveedorRepository.findById(entry.getProveedorId()).get();
    //     return kardexRepository.save(new Kardex(entry.getFecha(),proveedor));
    // }

    @PostMapping(value="/save", 
    produces = MediaType.APPLICATION_JSON_VALUE, 
    consumes = MediaType.APPLICATION_JSON_VALUE)
     public Kardex save(@RequestBody EntryKardexWrapper entry) throws Exception{
         Proveedor proveedor = proveedorRepository.findById(entry.getProveedorId()).get();
         Kardex entryKardex = kardexRepository.save(new Kardex(entry.getFecha(),proveedor));

         if (entryKardex.isClose())
             throw new Exception("Error: Entrada cerrada, no se puede guardar cambios");

         entryKardex.getItems().clear();
         if(!entry.getSerieId().isEmpty()) {
             KardexSerie k_serie;
             k_serie = ksr.findById(entry.getSerieId()).get();
             entryKardex.setSerie( k_serie );
         }
         entry.getPesos().forEach(peso -> {
            entryKardex
             .setJabaRecepcionada( entryKardex.getJabaRecepcionada()+peso.getCantidad() );
             ItemKardexDetail ikd = new ItemKardexDetail(peso.getCantidad(),peso.getPeso());
             ikd = ikr.save(ikd);
             entryKardex.getItems().add( ikd );
         });
         return kardexRepository.save(entryKardex);
    }

    @PostMapping(value="/switch_close_entry",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean closeEntry(@RequestBody IdWrapper idwrapper) throws Exception{
        Kardex entry = kardexRepository.findById( idwrapper.getId() ).get();
        entry.setClose(!entry.isClose());
        return entry.isClose();
    }

    @PutMapping("/addPeso")
    public Kardex addPeso(@RequestBody AddPesoWrapper wrapper){
        Kardex currentKardex = kardexRepository.findById(wrapper.getIdKardex()).get();
        currentKardex.getItems()
        .add(ikr.findById(wrapper.getIdItem()).get());
        currentKardex.setJabaRecepcionada(0);
        //Actualizar el numero de jabas recepcionadas
        currentKardex.getItems().forEach(item->{
            currentKardex.setJabaRecepcionada( currentKardex.getJabaRecepcionada() + item.getCantidad() );
        });
        return kardexRepository.save(currentKardex);
    }

    @PostMapping(value="/addPesos", 
    produces = MediaType.APPLICATION_JSON_VALUE, 
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public Kardex addPesos( @RequestBody ItemsKardexWrapper rq){
        Kardex currentKardex = kardexRepository.findById(rq.getIdKardex()).get();
        currentKardex.getItems().clear();

        rq.getPesos().forEach(peso -> {
            currentKardex
            .setJabaRecepcionada( currentKardex.getJabaRecepcionada()+peso.getCantidad() );
            ItemKardexDetail entry = new ItemKardexDetail(peso.getCantidad(),peso.getPeso());
            entry = ikr.save(entry);
            currentKardex.getItems().add( entry );
        });
        return kardexRepository.save(currentKardex);
    }

    @PostMapping(value="/lista", 
    produces = MediaType.APPLICATION_JSON_VALUE, 
    consumes = MediaType.APPLICATION_JSON_VALUE)    
    public HashMap<String, String> lista(@RequestBody List< PesoWrapper> pesos ){
        HashMap<String, String> map_rsp = new HashMap<>();
        map_rsp.put("length", String.valueOf(pesos.size()));
        return map_rsp;
    }

}
