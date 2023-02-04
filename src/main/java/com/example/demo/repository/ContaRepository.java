package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Query(value ="SELECT * FROM clientes WHERE nome in(:nomesLista)", nativeQuery = true)
    List<Conta> filtroNomes(List<String> nomesLista);

    
    
}
