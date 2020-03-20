package com.jmtp.jabakardex.controller;

import java.util.List;

import com.jmtp.jabakardex.model.ItemsEntrada;
import com.jmtp.jabakardex.repository.ItemBoletaDetailRepository;

import com.jmtp.jabakardex.repository.TipoJabaMatrizRepository;
import com.jmtp.jabakardex.repository.ItemTipoJabaRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/item_entrada")
public class ItemEntradaController {

    private ItemBoletaDetailRepository<ItemsEntrada> ibdr;
    private TipoJabaMatrizRepository tjmr;
    private ItemTipoJabaRepository tjr;

    public ItemEntradaController(
            ItemBoletaDetailRepository<ItemsEntrada> ibdr,
            TipoJabaMatrizRepository tjmr,
            ItemTipoJabaRepository tjr
            ){
        this.ibdr = ibdr;
        this.tjmr = tjmr;
        this.tjr = tjr;
    }

    @GetMapping("/all")
    public List<ItemsEntrada> getAll(){
        return ibdr.findAll();
    }

    @GetMapping("/get/{itemId}")
    public ItemsEntrada getItemsEntrada(@PathVariable(name = "itemId") String id){
        return ibdr.findById(id).get();
    }

    @PutMapping("/save")
    public ItemsEntrada save(@RequestBody ItemsEntrada item){
        return ibdr.save(item);
    }

//    @PostMapping(value="/add_tipojaba",
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ItemBoletaDetail addTipoJaba(@RequestBody ItemBoletaTipoJabaWrapper obj) throws Exception {
//        ItemBoletaDetail ikd = ibdr.findById(obj.getIdItemBoletal()).get();
//
//        if( ikd.getCantidad() < ikd.tipoJabaLenght()+obj.getCantidad() )
//            throw new Exception("No se puede especificar un numero mayor " +
//                    "de tipos de jabas que la cantidad de jabas de la pesa.");
//
//        TipoJabaMatriz tj = tjmr.findByAbreviacion( obj.getAbreviacion() );
//        TipoJaba _tj = new TipoJaba( obj.getCantidad(), tj );
//        tjr.save( _tj );
//        ikd.getTipoJaba().add( _tj );
//        return ibdr.save( ikd );
//    }

}
