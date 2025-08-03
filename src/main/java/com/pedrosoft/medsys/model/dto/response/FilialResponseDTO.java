package com.pedrosoft.medsys.model.dto.response;

import com.pedrosoft.medsys.model.entities.Medicamento;
import com.pedrosoft.medsys.model.entities.Venda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilialResponseDTO {
    private Long id;
    private String endereco;
    private String telefone;
    private List<Medicamento> medicamentos;
    private List<Venda> vendas;

}
