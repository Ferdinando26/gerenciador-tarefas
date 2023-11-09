package com.gerenciador.tarefas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApiController {

    @GetMapping("/test-api")
    public String test(){
        return "Sucesso";
    }

    @GetMapping("/test-api-bem-vindo")
    public String testBemVindo(@RequestParam (name = "nome") String nome){

        return "Bem Vindo "+ nome;
    }
}
