package com.jmtp.jabakardex.controller;

import com.jmtp.jabakardex.model.ItemsSalida;
import com.jmtp.jabakardex.repository.ItemBoletaDetailRepository;
import com.jmtp.jabakardex.repository.ItemTipoJabaRepository;
import com.jmtp.jabakardex.repository.TipoJabaMatrizRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/item_salida")
public class ItemSalidaController {

    private ItemBoletaDetailRepository<ItemsSalida> ibdRepo;
    private TipoJabaMatrizRepository tjmRepo;
    private ItemTipoJabaRepository tjRepo;

    public ItemSalidaController(
            ItemBoletaDetailRepository<ItemsSalida> ibdRepo,
            TipoJabaMatrizRepository tjmRepo,
            ItemTipoJabaRepository tjRepo) {
        this.ibdRepo = ibdRepo;
        this.tjmRepo = tjmRepo;
        this.tjRepo = tjRepo;
    }

    @GetMapping("/all")
    public List<ItemsSalida> getAll(){
        return ibdRepo.findAll();
    }

    @GetMapping("/get/{itemId}")
    public ItemsSalida getItemsEntrada(@PathVariable(name = "itemId") String id){
        return ibdRepo.findById(id).get();
    }

    @PutMapping("/save")
    public ItemsSalida save(@RequestBody ItemsSalida item){
        return ibdRepo.save(item);
    }

}
