package com.example.demo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.errors.exception.ModelNotFoundException;
import com.example.demo.models.Fornecedor;
import com.example.demo.repository.FornecedorRepository;

@Service
public class FornecedorService {

  @Autowired
  FornecedorRepository persistir;

  public Object create(Fornecedor fornecedor) {
    return persistir.save(fornecedor);
  }

  public Object findAll() {
    return persistir.findAll();
  }

  public Fornecedor findById(Long id) {
    Optional<Fornecedor> objeto = persistir.findById(id);
    return objeto.orElseThrow(() -> new ModelNotFoundException("Fornecedor não encontado"));
  }

  public void deleteById(Long id) {
    Optional<Fornecedor> objeto = persistir.findById(id);
    objeto.ifPresentOrElse((obj) -> {
      persistir.deleteById(obj.getId());
    }, () -> new ModelNotFoundException("Fornecedor não encontado"));
  }

  public Object update(Fornecedor fornecedor) {
    return persistir.save(fornecedor);
  }

  public boolean existsById(Long id) {
    return persistir.existsById(id);
  }

  public List<Fornecedor> filtroNomes(String nomes) {
    List<String> nomesLista = new ArrayList<String>(Arrays.asList(nomes.split(",")));
    return persistir.filtroNomes(nomesLista);
  }
}
