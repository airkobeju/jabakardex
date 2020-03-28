package com.jmtp.jabakardex.repository;

import com.jmtp.jabakardex.model.SerieBoleta;
import com.jmtp.jabakardex.model.TipoOperacion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SerieBoletaRepository extends MongoRepository<SerieBoleta, String> {

    SerieBoleta findByValue(String value);

    List<SerieBoleta> findByOperacion(TipoOperacion operacion);

}
