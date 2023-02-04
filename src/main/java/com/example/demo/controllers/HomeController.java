package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.model_views.Home;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Home", description = "Tela de inicial")
public class HomeController {

    @GetMapping("/")
    public Home name() {
        // return "Bem vindo\nDocumentação: http://localhost:8080/swagger-ui/index.html";
        return new Home();
    }
}
