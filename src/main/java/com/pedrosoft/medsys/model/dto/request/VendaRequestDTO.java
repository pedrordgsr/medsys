package com.pedrosoft.medsys.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VendaRequestDTO {

    @Valid
    @NotBlank
    private Long cliente_id;

    @Valid
    @NotBlank
    private Long filial_id;

    @NotNull
    @Size(min = 1)
    private List<MedicamentoVendaRequestDTO> itens;
}
