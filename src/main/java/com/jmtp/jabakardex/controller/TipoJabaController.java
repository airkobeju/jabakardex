package com.jmtp.jabakardex.controller;

import com.jmtp.jabakardex.model.ItemTipoJaba;
import com.jmtp.jabakardex.repository.ItemTipoJabaRepository;
import com.jmtp.jabakardex.utils.IdWrapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/tipojaba")
public class TipoJabaController {

    private ItemTipoJabaRepository tjr;

    public TipoJabaController(ItemTipoJabaRepository tjr) {
        this.tjr = tjr;
    }

    @GetMapping("/all")
    public List<ItemTipoJaba> getAll(){
        return tjr.findAll( Sort.by(Sort.Direction.DESC, "id" ));
    }

    @PostMapping("/get")
    public ItemTipoJaba getKardexEntry(@RequestBody IdWrapper idwrapper){
        return tjr.findById(idwrapper.getId()).get();
    }

    @PostMapping(value="/save",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ItemTipoJaba save(@RequestBody ItemTipoJaba tjaba){
        return tjr.save(tjaba);
    }

}
