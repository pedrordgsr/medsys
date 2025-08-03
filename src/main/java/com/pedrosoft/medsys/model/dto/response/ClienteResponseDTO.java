package com.pedrosoft.medsys.model.dto.response;

import com.pedrosoft.medsys.model.entities.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {

    private Long id;
    private String nome;
    private String cpf;

    public ClienteResponseDTO(Cliente response) {
        this.id = response.getId();
        this.nome = response.getNome();
        this.cpf = response.getCpf();
    }

    @Override
    public String toString() {
        return "Resposta: {" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
