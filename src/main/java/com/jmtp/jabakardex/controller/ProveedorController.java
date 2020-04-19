package com.jmtp.jabakardex.controller;

import java.util.List;

import com.jmtp.jabakardex.model.Proveedor;
import com.jmtp.jabakardex.repository.ProveedorRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/proveedor")
public class ProveedorController {

    private ProveedorRepository proveedorRepository;

    public ProveedorController(ProveedorRepository proveedorRepository){
        this.proveedorRepository = proveedorRepository;
    }

    @GetMapping(path={"","/all"})
    public List<Proveedor> getAll(){
        return proveedorRepository.findAll();
    }

    @GetMapping("/get/{proveedorId}")
    public Proveedor getAlumno(@PathVariable(name = "proveedorId") String id){
        return proveedorRepository.findById(id).get();
    }

    @PutMapping("/save")
    public Proveedor save(@RequestBody Proveedor proveedor){
        return proveedorRepository.save(proveedor);
    }

}
