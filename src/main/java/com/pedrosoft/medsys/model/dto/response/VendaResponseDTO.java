package com.pedrosoft.medsys.model.dto.response;

import com.pedrosoft.medsys.model.entities.Cliente;
import com.pedrosoft.medsys.model.entities.Filial;
import com.pedrosoft.medsys.model.entities.Venda;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class VendaResponseDTO {
    private Long id;
    private LocalDateTime dataHora;
    private BigDecimal total;
    private Long id_cliente;
    private Long id_filial;
    private List<MedicamentoVendaResponseDTO> itens;

    public VendaResponseDTO(Venda venda) {
        this.id = venda.getId();
        this.dataHora = venda.getDataHora();
        this.total = venda.getTotal();
        this.id_cliente = venda.getCliente().getId();
        this.id_filial = venda.getFilial().getId();
        this.itens = venda.getItens().stream()
                .map(MedicamentoVendaResponseDTO::new)
                .collect(Collectors.toList());
    }
}
