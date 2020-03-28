package com.jmtp.jabakardex.controller;

import com.jmtp.jabakardex.model.TipoOperacion;
import com.jmtp.jabakardex.repository.TipoOperacionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/operacion")
public class TipoOperacionController {

    private TipoOperacionRepository opRepo;

    public TipoOperacionController(TipoOperacionRepository opRepo) {
        this.opRepo = opRepo;
    }

    @GetMapping(path = {"","/all"})
    public List<TipoOperacion> getAll(){
        return opRepo.findAll();
    }

    @PostMapping(path = {"","/save"})
    public TipoOperacion save(@RequestBody TipoOperacion operacion){
        return opRepo.save(operacion);
    }

    @GetMapping(path = {"/{id}"})
    public TipoOperacion findById(@PathVariable(name = "id") String id){
        return opRepo.findById(id).get();
    }

}
