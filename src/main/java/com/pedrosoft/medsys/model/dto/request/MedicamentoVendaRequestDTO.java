package com.pedrosoft.medsys.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicamentoVendaRequestDTO {

    @NotNull
    private Long medicamentoId;

    @NotNull
    private Integer quantidade;

    @NotNull
    private Boolean receita;
}
