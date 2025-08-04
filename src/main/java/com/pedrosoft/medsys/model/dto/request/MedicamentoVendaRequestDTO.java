package com.pedrosoft.medsys.model.dto.request;

import jakarta.validation.constraints.NotNull;

public class MedicamentoVendaRequestDTO {

    @NotNull
    private Long medicamentoId;

    @NotNull
    private Integer quantidade;

    @NotNull
    private Boolean receita;
}
