package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Fornecedor;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    @Query(value ="SELECT * FROM fornecedores WHERE nome in(:nomesLista)", nativeQuery = true)
    List<Fornecedor> filtroNomes(List<String> nomesLista);
    
}
