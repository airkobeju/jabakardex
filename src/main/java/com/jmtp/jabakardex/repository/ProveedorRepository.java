package com.jmtp.jabakardex.repository;

import com.jmtp.jabakardex.model.Proveedor;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProveedorRepository extends MongoRepository<Proveedor, String> {

    Proveedor findByName(String name);

}