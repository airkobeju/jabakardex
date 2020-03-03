package com.jmtp.jabakardex.controller;

import java.util.List;

import com.jmtp.jabakardex.model.ItemKardexDetail;
import com.jmtp.jabakardex.model.TipoJaba;
import com.jmtp.jabakardex.model.TipoJabaMatriz;
import com.jmtp.jabakardex.repository.ItemKardexDetailRepository;

import com.jmtp.jabakardex.repository.TipoJabaMatrizRepository;
import com.jmtp.jabakardex.repository.TipoJabaRepository;
import com.jmtp.jabakardex.utils.ItemKardexTipoJabaWrapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/itemkardexdetail")
public class ItemKardexDetailController {

    private ItemKardexDetailRepository ikdr;
    private TipoJabaMatrizRepository tjmr;
    private TipoJabaRepository tjr;

    public ItemKardexDetailController(
            ItemKardexDetailRepository item,
            TipoJabaMatrizRepository tjmr,
            TipoJabaRepository tjr
            ){
        this.ikdr = item;
        this.tjmr = tjmr;
        this.tjr = tjr;
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
    public ItemKardexDetail addTipoJaba(@RequestBody ItemKardexTipoJabaWrapper obj) throws Exception {
        ItemKardexDetail ikd = ikdr.findById(obj.getIdItemKardexDetail()).get();

        if( ikd.getCantidad() < ikd.tipoJabaLenght()+obj.getCantidad() )
            throw new Exception("No se puede especificar un numero mayor " +
                    "de tipos de jabas que la cantidad de jabas de la pesa.");

        TipoJabaMatriz tj = tjmr.findByAbreviacion( obj.getAbreviacion() );
        TipoJaba _tj = new TipoJaba( obj.getCantidad(), tj );
        tjr.save( _tj );
        ikd.getTipoJaba().add( _tj );
        return ikdr.save( ikd );
    }

}
