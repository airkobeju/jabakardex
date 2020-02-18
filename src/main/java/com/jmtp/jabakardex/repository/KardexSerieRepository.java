package com.jmtp.jabakardex.repository;

import com.jmtp.jabakardex.model.KardexSerie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface KardexSerieRepository extends MongoRepository<KardexSerie, String> {

    List<KardexSerie> findByValue(String value);

}
