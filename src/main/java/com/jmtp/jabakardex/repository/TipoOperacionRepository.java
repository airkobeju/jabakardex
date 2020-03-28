package com.jmtp.jabakardex.repository;

import com.jmtp.jabakardex.model.TipoOperacion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TipoOperacionRepository extends MongoRepository<TipoOperacion, String> {

    TipoOperacion findByName(String name);

}
