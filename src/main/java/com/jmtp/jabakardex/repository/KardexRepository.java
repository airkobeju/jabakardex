package com.jmtp.jabakardex.repository;

import com.jmtp.jabakardex.model.Kardex;
import com.jmtp.jabakardex.model.Proveedor;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface KardexRepository extends MongoRepository<Kardex, String> {

    List<Kardex> findByProveedor(Proveedor proveedor);

    List<Kardex> findByProveedorName(Proveedor proveedor);

    List<Kardex> findByFecha(Date fecha);

}
