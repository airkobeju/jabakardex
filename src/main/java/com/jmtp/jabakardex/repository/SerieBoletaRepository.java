package com.jmtp.jabakardex.repository;

import com.jmtp.jabakardex.model.SerieBoleta;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SerieBoletaRepository extends MongoRepository<SerieBoleta, String> {

    SerieBoleta findByValue(String value);

}
