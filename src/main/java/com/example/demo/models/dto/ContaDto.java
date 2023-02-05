package com.example.demo.models.dto;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;

import com.example.demo.models.Conta;

import lombok.Data;

@Data
public class ContaDto {
    private Long id;
    private String nome;
    private String descricao;
    private boolean status;
    private BigDecimal preco;
    private Double fornecedor;

    public static Conta convertDtoForObject(ContaDto contaDto) {
        Conta contaRetorno = new Conta();
        BeanUtils.copyProperties(contaDto, contaRetorno);
        return contaRetorno;
    }
}
