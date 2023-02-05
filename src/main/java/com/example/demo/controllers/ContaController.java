package com.example.demo.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.errors.exception.ModelNotFoundException;
import com.example.demo.models.Conta;
import com.example.demo.models.dto.ContaDto;
import com.example.demo.models.model_views.Response;
import com.example.demo.services.ContaService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/conta")
@Tag(name = "Conta", description = "contas")
public class ContaController {

    private final ContaService service;
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Conta>> create(@RequestBody ContaDto conta){
        Response<Conta> response = new Response<Conta>();
        try {
            response.setData(service.create(conta));
            
        } catch(ModelNotFoundException e){
            response.setErros(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody Conta conta){
        boolean isConta = service.existsById(conta.getId());
            if(!isConta){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        return new ResponseEntity<>(service.update(conta), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Page<Conta>>> all(  @PageableDefault( page = 0,
            size = 10) Pageable page){
        Response<Page<Conta>> response = new Response<>();
        response.setData(service.findAll(page));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<Conta>> finById(@PathVariable("id") Long id){
        Response<Conta> response = new Response<>();
        try {
            response.setData(service.findById(id));
        } catch (ModelNotFoundException e){
            e.printStackTrace();
            response.setErros(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.setErros(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        service.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/filtro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> filtro(@PathParam("nomes") String nomes){
        List<Conta> contaes = service.filtroNomes(nomes);
        return new ResponseEntity<>(contaes, HttpStatus.OK);
    }
}
