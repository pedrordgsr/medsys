package com.pedrosoft.medsys.model.dto.request;

import com.pedrosoft.medsys.model.entities.Tipo;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MedicamentoRequestDTO {

    @NotBlank
    private String nome;

    @NotNull
    @DecimalMin("0.00")
    private BigDecimal preco;

    @NotNull
    private Tipo tipo;

    @NotNull
    private Long filialId;
}
