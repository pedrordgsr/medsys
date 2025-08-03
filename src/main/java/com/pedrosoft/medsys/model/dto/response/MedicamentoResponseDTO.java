package com.pedrosoft.medsys.model.dto.response;

import com.pedrosoft.medsys.model.entities.Filial;
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
    private Long id;
    private String nome;
    private BigDecimal preco;
    private Tipo tipo;
    private Filial filial;
}
