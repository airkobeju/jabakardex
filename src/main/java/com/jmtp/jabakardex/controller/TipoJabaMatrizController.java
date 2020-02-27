package com.jmtp.jabakardex.controller;

import com.jmtp.jabakardex.model.TipoJabaMatriz;
import com.jmtp.jabakardex.repository.TipoJabaMatrizRepository;
import com.jmtp.jabakardex.utils.IdWrapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/tipojabamatriz")
public class TipoJabaMatrizController {

    private TipoJabaMatrizRepository tjmr;

    public TipoJabaMatrizController(TipoJabaMatrizRepository tjmr) {
        this.tjmr = tjmr;
    }

    @GetMapping("/all")
    public List<TipoJabaMatriz> getAll(){
        return tjmr.findAll( Sort.by(Sort.Direction.ASC, "name") );
    }

    @PostMapping("/get")
    public TipoJabaMatriz getKardexEntry(@RequestBody IdWrapper idwrapper){
        return tjmr.findById(idwrapper.getId()).get();
    }

    @PostMapping(value="/save",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public TipoJabaMatriz save (@RequestBody TipoJabaMatriz tipojaba){
        return tjmr.save(tipojaba);
    }

}
