package com.pedrosoft.medsys.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilialRequestDTO {

    @Valid
    @NotBlank
    private String endereco;

    @Valid
    @NotBlank
    private String telefone;

}
