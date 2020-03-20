package com.jmtp.jabakardex.repository;

import com.jmtp.jabakardex.model.Boleta;
import com.jmtp.jabakardex.model.Proveedor;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface BoletaRepository extends MongoRepository<Boleta, String> {

    List<Boleta> findByProveedor(Proveedor proveedor);

    List<Boleta> findByFecha(Date fecha);

}
