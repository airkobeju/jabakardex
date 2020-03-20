package com.jmtp.jabakardex.repository;

import com.jmtp.jabakardex.model.Proveedor;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProveedorRepository extends MongoRepository<Proveedor, String> {

    Proveedor findByName(String name);

    List<Proveedor> findByLastName(String lastName);

}