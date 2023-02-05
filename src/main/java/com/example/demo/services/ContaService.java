package com.example.demo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.errors.exception.ModelNotFoundException;
import com.example.demo.models.Conta;
import com.example.demo.models.Fornecedor;
import com.example.demo.models.dto.ContaDto;
import com.example.demo.repository.ContaRepository;

import jakarta.persistence.EntityManager;

@Service
public class ContaService {

  @Autowired
  ContaRepository persistir;

  @Autowired
  FornecedorService fornecedorService;

  @Autowired
  private EntityManager entityManager;

  public Conta create(ContaDto contaDto) throws ModelNotFoundException {
    if(!fornecedorService.existsById(contaDto.getFornecedor().longValue()))
            throw new ModelNotFoundException("Fornecedor não encontrado");
        
    Fornecedor fornecedor = fornecedorService.findById(contaDto.getFornecedor().longValue());
    Fornecedor fornecedorRef = entityManager.getReference(Fornecedor.class, fornecedor.getId());
    Conta contaObject = ContaDto.convertDtoForObject(contaDto);
    contaObject.setFornecedor(fornecedorRef);
    return persistir.save(contaObject);
  }

  public Page<Conta> findAll(Pageable page) {
    return persistir.findAll(page);
  }

  public Conta findById(Long id) throws ModelNotFoundException {
    Optional<Conta> objeto = persistir.findById(id);
    return objeto.orElseThrow(() -> new ModelNotFoundException("Conta não encontrada"));
  }

  public void deleteById(Long id) {
    Optional<Conta> objeto = persistir.findById(id);
    objeto.ifPresentOrElse((obj) -> {
      persistir.deleteById(obj.getId());
    }, () -> new ModelNotFoundException("Conta não encontrada"));
  }

  public Object update(Conta fornecedor) {
    return persistir.save(fornecedor);
  }

  public boolean existsById(Long id) {
    return persistir.existsById(id);
  }

  public List<Conta> filtroNomes(String nomes) {
    List<String> nomesLista = new ArrayList<String>(Arrays.asList(nomes.split(",")));
    return persistir.filtroNomes(nomesLista);
  }
}
