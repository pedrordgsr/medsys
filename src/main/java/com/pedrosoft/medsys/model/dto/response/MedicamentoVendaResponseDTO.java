package com.pedrosoft.medsys.model.dto.response;

import com.pedrosoft.medsys.model.entities.MedicamentoVenda;

import java.math.BigDecimal;

public class MedicamentoVendaResponseDTO {

    private Long medicamentoId;
    private String medicamentoNome;
    private BigDecimal valorUnitario;
    private Integer quantidade;
    private BigDecimal subtotal;
    private Boolean receita;

    public MedicamentoVendaResponseDTO(MedicamentoVenda item) {
        this.medicamentoId = item.getMedicamento().getId();
        this.medicamentoNome = item.getMedicamento().getNome();
        this.valorUnitario = item.getValorUnitario();
        this.quantidade = item.getQuantidade();
        this.subtotal = valorUnitario.multiply(BigDecimal.valueOf(quantidade));
    }
}
