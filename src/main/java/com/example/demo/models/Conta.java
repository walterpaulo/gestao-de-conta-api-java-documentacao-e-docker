package com.example.demo.models;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@Entity(name = "contas")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private boolean status;
    private BigDecimal preco;
    private Date dataInclucao;
    private Date dataAlteracao;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "forncedor_id")
    private Fornecedor fornecedor;

    @PrePersist
    public void create(){
        this.status = (this.status == true) ? this.status : true;
        this.dataInclucao = (this.dataInclucao == null) ? new Date() : this.dataInclucao;
        this.dataAlteracao = (this.dataAlteracao == null) ? new Date() : this.dataAlteracao;
    }

    @PreUpdate
    public void update(){
        this.dataAlteracao = new Date();
    }

}
