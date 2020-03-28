package com.jmtp.jabakardex.controller;

import java.util.List;

import com.jmtp.jabakardex.model.SerieBoleta;
import com.jmtp.jabakardex.model.TipoOperacion;
import com.jmtp.jabakardex.repository.SerieBoletaRepository;
import com.jmtp.jabakardex.repository.TipoOperacionRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/serieboleta")
public class SerieBoletaController {

    private SerieBoletaRepository ksr;
    private TipoOperacionRepository opRepo;

    public SerieBoletaController(
            SerieBoletaRepository ksr,
            TipoOperacionRepository opRepo) {
        this.ksr = ksr;
        this.opRepo = opRepo;
    }

    @GetMapping(path = {"","/all"})
    public List<SerieBoleta> getAll(){
        return ksr.findAll();
    }

    @GetMapping(path = {"{id}"})
    public SerieBoleta findById(@PathVariable(name = "id") String id){
        return ksr.findById(id).get();
    }
    
    @PostMapping(path={"/by_operacion"},
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    List<SerieBoleta> findByOperacion(@RequestBody TipoOperacion operacion){
        return ksr.findByOperacion(operacion);
    }

    @GetMapping(path = {"/by_operacion/{name}"})
    List<SerieBoleta> findByOperacionName(@PathVariable(name = "name") String name){
        return ksr.findByOperacion(opRepo.findByName(name));
    }

    @PostMapping(path={"/"},
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public SerieBoleta save(@RequestBody SerieBoleta serie){
        return ksr.save(serie);
    }

}
