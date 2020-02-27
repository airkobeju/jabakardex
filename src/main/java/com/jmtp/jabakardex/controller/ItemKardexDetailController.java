package com.jmtp.jabakardex.controller;

import java.util.List;

import com.jmtp.jabakardex.model.ItemKardexDetail;
import com.jmtp.jabakardex.model.TipoJaba;
import com.jmtp.jabakardex.model.TipoJabaMatriz;
import com.jmtp.jabakardex.repository.ItemKardexDetailRepository;

import com.jmtp.jabakardex.repository.TipoJabaMatrizRepository;
import com.jmtp.jabakardex.utils.ItemKardexTipoJabaWrapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/itemkardexdetail")
public class ItemKardexDetailController {

    private ItemKardexDetailRepository ikdr;
    private TipoJabaMatrizRepository tjmr;

    public ItemKardexDetailController(
            ItemKardexDetailRepository item,
            TipoJabaMatrizRepository tjmr
            ){
        this.ikdr = item;
        this.tjmr = tjmr;
    }

    @GetMapping("/all")
    public List<ItemKardexDetail> getAll(){
        return ikdr.findAll();
    }

    @GetMapping("/get/{itemId}")
    public ItemKardexDetail getItemKardexDetail(@PathVariable(name = "itemId") String id){
        return ikdr.findById(id).get();
    }

    @PutMapping("/save")
    public ItemKardexDetail save(@RequestBody ItemKardexDetail item){
        return ikdr.save(item);
    }

    @PostMapping(value="/add_tipojaba",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ItemKardexDetail addTipoJaba(@RequestBody ItemKardexTipoJabaWrapper obj){
        ItemKardexDetail ikd = ikdr.findById(obj.getIdItemKardexDetail()).get();
        TipoJabaMatriz tj = tjmr.findByAbreviacion( obj.getAbreviacion() );
        ikd.getTipoJaba().add( new TipoJaba( obj.getCantidad(), tj ));
        return ikdr.save( ikd );
    }

}