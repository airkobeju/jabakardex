package com.jmtp.jabakardex.controller;

import com.jmtp.jabakardex.model.Kardex;
import com.jmtp.jabakardex.model.TipoJaba;
import com.jmtp.jabakardex.repository.TipoJabaRepository;
import com.jmtp.jabakardex.utils.IdWrapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/tipojaba")
public class TipoJabaController {

    private TipoJabaRepository tjr;

    public TipoJabaController(TipoJabaRepository tjr) {
        this.tjr = tjr;
    }

    @GetMapping("/all")
    public List<TipoJaba> getAll(){
        return tjr.findAll( Sort.by(Sort.Direction.DESC, "id" ));
    }

    @PostMapping("/get")
    public TipoJaba getKardexEntry(@RequestBody IdWrapper idwrapper){
        return tjr.findById(idwrapper.getId()).get();
    }

    @PostMapping(value="/save",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public TipoJaba save(@RequestBody TipoJaba tjaba){
        return null;
    }

}
