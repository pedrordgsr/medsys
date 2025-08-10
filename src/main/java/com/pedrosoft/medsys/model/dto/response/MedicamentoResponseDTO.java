package com.pedrosoft.medsys.model.dto.response;

import com.pedrosoft.medsys.model.entities.Filial;
import com.pedrosoft.medsys.model.entities.Medicamento;
import com.pedrosoft.medsys.model.entities.Tipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentoResponseDTO {

    public MedicamentoResponseDTO(Medicamento medicamento) {
        this.id = medicamento.getId();
        this.nome = medicamento.getNome();
        this.tipo = medicamento.getTipo();
        this.preco = medicamento.getPreco();
        this.estoque = medicamento.getEstoque();
        this.filial_id = medicamento.getFilial().getId();
    }

    private Long id;
    private String nome;
    private BigDecimal preco;
    private Tipo tipo;
    private Long filial_id;
    private int estoque;


}
