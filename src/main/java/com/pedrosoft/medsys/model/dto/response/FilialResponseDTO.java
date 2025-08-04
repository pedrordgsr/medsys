package com.pedrosoft.medsys.model.dto.response;

import com.pedrosoft.medsys.model.entities.Filial;
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

    public FilialResponseDTO(Filial filial) {
        this.id = filial.getId();
        this.endereco = filial.getEndereco();
        this.telefone = filial.getTelefone();
    }
    private Long id;
    private String endereco;
    private String telefone;

}
