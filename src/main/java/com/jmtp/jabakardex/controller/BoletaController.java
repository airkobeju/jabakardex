package com.jmtp.jabakardex.controller;

import java.util.HashMap;
import java.util.List;

import com.jmtp.jabakardex.model.*;
import com.jmtp.jabakardex.repository.*;
import com.jmtp.jabakardex.utils.CompraPesosWrapper;
import com.jmtp.jabakardex.utils.IdWrapper;
import com.jmtp.jabakardex.utils.PesoWrapper;

import com.jmtp.jabakardex.utils.Utils;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/boleta")
public class BoletaController {

    private BoletaRepository boletaRepository;
    private ProveedorRepository proveedorRepository;
    private ItemBoletaDetailRepository<ItemsEntrada>  entradasRepo;
    private ItemBoletaDetailRepository<ItemsSalida>  salidasRepo;
    private SerieBoletaRepository ksr;
    private ItemTipoJabaRepository tipoJabaRepo;
    private TipoJabaMatrizRepository tjmRepo;

    public BoletaController(
        BoletaRepository boletaRepository,
        ProveedorRepository proveedorRepository,
        ItemBoletaDetailRepository<ItemsEntrada>  entradasRepo,
        ItemBoletaDetailRepository<ItemsSalida>  salidasRepo,
        SerieBoletaRepository ksr,
        ItemTipoJabaRepository tipoJabaRepo,
        TipoJabaMatrizRepository tjmRepo){
        this.boletaRepository = boletaRepository;
        this.proveedorRepository = proveedorRepository;
        this.entradasRepo = entradasRepo;
        this.salidasRepo = salidasRepo;
        this.ksr = ksr;
        this.tipoJabaRepo = tipoJabaRepo;
        this.tjmRepo = tjmRepo;
    }

    @GetMapping(path = {"", "/all"})
    public List<Boleta> getAll(){
        return boletaRepository.findAll(Sort.by(Sort.Direction.DESC, "fecha"));
    }

    @PostMapping("/get")
    public Boleta getKardexEntry(@RequestBody IdWrapper idwrapper){
        return boletaRepository.findById(idwrapper.getId()).get();
    }

    @GetMapping("/{id}")
    public Boleta getEntryById(@PathVariable(name = "id") String id){
        return boletaRepository.findById(id).get();
    }

    @PostMapping(path={"", "/save"},
    produces = MediaType.APPLICATION_JSON_VALUE, 
    consumes = MediaType.APPLICATION_JSON_VALUE)
     public Boleta save(@RequestBody Boleta entry) throws Exception{
        if (entry.isClose())
            throw new Exception("Error: Boleta cerrada, no se puede guardar cambios");

        TipoJabaMatriz tjDefalt = null;
        if(entry.getProveedor().getTipoJaba() != null)
            tjDefalt = entry.getProveedor().getTipoJaba();
        else{
            try{
                tjDefalt = tjmRepo.findByDefaultJaba(true);
            }catch (Exception err){
                System.out.println(err.getMessage());
            }

        }
        entry = Utils.boletaChecked(entry, tjDefalt);

        entry.getItemsEntrada().stream()
        .forEach(entrada ->{
            //Guardando todos los tipos de jabas de la entrada
            entrada.setTipoJaba(tipoJabaRepo.saveAll(entrada.getTipoJaba()));
        });
        //Guardando todas las entradas de la entrada y seteandolas en el objeto entry
        entry.setItemsEntrada(entradasRepo.saveAll( entry.getItemsEntrada()));

        entry.getItemsSalida().stream()
                .forEach(salida ->{
                    //Guardando todos los tipos de jabas dela salida
                    salida.setTipoJaba(tipoJabaRepo.saveAll(salida.getTipoJaba()));
                });
        //Guardando todas las entradas de la salida y seteandolas en el objeto entry
        entry.setItemsSalida(salidasRepo.saveAll( entry.getItemsSalida()));

         return boletaRepository.save(entry);
    }

    @PostMapping(value="/switch_close_entry",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean closeEntry(@RequestBody IdWrapper idwrapper) throws Exception{
        Boleta entry = boletaRepository.findById( idwrapper.getId() ).get();
        entry.setClose(!entry.isClose());
        return entry.isClose();
    }

    @PostMapping(value="/addPesos", 
    produces = MediaType.APPLICATION_JSON_VALUE, 
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public Boleta addCompraPesos(@RequestBody CompraPesosWrapper cpw){
        Boleta currentBoleta = boletaRepository.findById(cpw.getIdBoleta()).get();
        currentBoleta.getItemsEntrada().clear();

        cpw.getPesos()
        .forEach(peso -> {
            currentBoleta.getItemsEntrada().add( entradasRepo.save( peso ) );
        });
        return boletaRepository.save(currentBoleta);
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
