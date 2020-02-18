package com.jmtp.jabakardex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public class MainController{

    public MainController(){

    }

    @GetMapping("index")
    public String index(){
        return "Hola desde JabaKardex";
    }

}