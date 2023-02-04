package com.example.demo.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@Entity(name = "fornecedores")
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Date dataInclucao;
    private Date dataAlteracao;

    @PrePersist
    public void create(){
        this.dataInclucao = (this.dataInclucao == null) ? new Date() : this.dataInclucao;
        this.dataAlteracao = (this.dataAlteracao == null) ? new Date() : this.dataAlteracao;
    }

    @PreUpdate
    public void update(){
        this.dataAlteracao = new Date();
    }

}
