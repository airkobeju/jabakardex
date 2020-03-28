package com.jmtp.jabakardex.repository;

import com.jmtp.jabakardex.model.TipoJabaMatriz;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TipoJabaMatrizRepository extends MongoRepository<TipoJabaMatriz, String> {

    TipoJabaMatriz findByAbreviacion(String abreviacion);

    TipoJabaMatriz findByDefaultJaba(boolean defaultJaba);

}
