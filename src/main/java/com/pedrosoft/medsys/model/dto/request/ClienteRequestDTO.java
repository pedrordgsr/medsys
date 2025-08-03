package com.pedrosoft.medsys.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteRequestDTO {

    @Valid
    @NotBlank
    private String nome;

    @Valid
    @NotBlank
    private String cpf;
}
