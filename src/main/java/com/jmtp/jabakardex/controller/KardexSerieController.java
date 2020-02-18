package com.jmtp.jabakardex.controller;

import com.jmtp.jabakardex.model.KardexSerie;
import com.jmtp.jabakardex.repository.KardexSerieRepository;
import com.jmtp.jabakardex.utils.IdWrapper;
import com.jmtp.jabakardex.utils.KardexSerieWrapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/kardexserie")
public class KardexSerieController {

    private KardexSerieRepository ksr;

    public KardexSerieController(KardexSerieRepository ksr) {
        this.ksr = ksr;
    }

    @GetMapping("/all")
    public List<KardexSerie> getAll(){
        return ksr.findAll();
    }

    @PostMapping(value = "/get")
    public KardexSerie getKardexSerie(@RequestBody IdWrapper idwrapper){
        return ksr.findById( idwrapper.getId() ).get();
    }

    @PostMapping(value="/save",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public KardexSerie save(@RequestBody KardexSerieWrapper ksw){
        return ksr.save( new KardexSerie(ksw.getValue(),ksw.getNote()));
    }

}
