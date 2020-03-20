package com.jmtp.jabakardex.controller;

import com.jmtp.jabakardex.model.SerieBoleta;
import com.jmtp.jabakardex.repository.SerieBoletaRepository;
import com.jmtp.jabakardex.utils.IdWrapper;
import com.jmtp.jabakardex.utils.KardexSerieWrapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/kardexserie")
public class KardexSerieController {

    private SerieBoletaRepository ksr;

    public KardexSerieController(SerieBoletaRepository ksr) {
        this.ksr = ksr;
    }

    @GetMapping("/all")
    public List<SerieBoleta> getAll(){
        return ksr.findAll();
    }

    @PostMapping(value = "/get")
    public SerieBoleta getKardexSerie(@RequestBody IdWrapper idwrapper){
        return ksr.findById( idwrapper.getId() ).get();
    }

    @PostMapping(value="/save",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public SerieBoleta save(@RequestBody KardexSerieWrapper ksw){
        return ksr.save( new SerieBoleta(ksw.getValue(),ksw.getNote()));
    }

}
