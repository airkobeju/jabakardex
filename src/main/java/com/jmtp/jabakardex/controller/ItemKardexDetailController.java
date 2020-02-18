package com.jmtp.jabakardex.controller;

import java.util.List;

import com.jmtp.jabakardex.model.ItemKardexDetail;
import com.jmtp.jabakardex.repository.ItemKardexDetailRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/itemkardexdetail")
public class ItemKardexDetailController {

    private ItemKardexDetailRepository ikdr;

    public ItemKardexDetailController(ItemKardexDetailRepository item){
        this.ikdr = item;
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


}