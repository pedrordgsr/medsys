package com.pedrosoft.medsys.service;

import com.pedrosoft.medsys.model.dto.request.MedicamentoVendaRequestDTO;
import com.pedrosoft.medsys.model.dto.request.VendaRequestDTO;
import com.pedrosoft.medsys.model.dto.response.VendaResponseDTO;
import com.pedrosoft.medsys.model.entities.*;
import com.pedrosoft.medsys.model.repository.ClienteDAO;
import com.pedrosoft.medsys.model.repository.FilialDAO;
import com.pedrosoft.medsys.model.repository.MedicamentoDAO;
import com.pedrosoft.medsys.model.repository.VendaDAO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    public final VendaDAO vendaDAO;
    public final MedicamentoDAO medicamentoDAO;
    public final FilialDAO filialDAO;
    public final ClienteDAO clienteDAO;

    @Autowired
    public VendaService(VendaDAO vendaDAO,
                        MedicamentoDAO medicamentoDAO,
                        FilialDAO filialDAO,
                        ClienteDAO clienteDAO){
        this.vendaDAO = vendaDAO;
        this.medicamentoDAO = medicamentoDAO;
        this.filialDAO = filialDAO;
        this.clienteDAO = clienteDAO;
    }

    public VendaResponseDTO create(VendaRequestDTO dto){
        Cliente cliente = clienteDAO.findById(dto.getCliente_id())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Filial filial = filialDAO.findById(dto.getFilial_id())
                .orElseThrow(() -> new IllegalArgumentException("Filial não encontrada"));

        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setFilial(filial);
        venda.setDataHora(LocalDateTime.now());
        venda.setItens(new ArrayList<>());

        BigDecimal total = BigDecimal.ZERO;

        for(MedicamentoVendaRequestDTO itemDto : dto.getItens()){
            Medicamento medicamento = medicamentoDAO.findById(itemDto.getMedicamentoId())
                    .orElseThrow(() -> new IllegalArgumentException("Medicamento não encontrado"));

            MedicamentoVenda item = new MedicamentoVenda();
            item.setVenda(venda);
            item.setMedicamento(medicamento);
            item.setQuantidade(itemDto.getQuantidade());
            item.setValorUnitario(medicamento.getPreco());
            item.setReceita(itemDto.getReceita());

            boolean isControlado = medicamento.getTipo() == Tipo.CONTROLADO;

            if (isControlado && !itemDto.getReceita()) {
                throw new IllegalArgumentException("Apresentação de receita é obrigatória para o medicamento: " + medicamento.getNome());
            }


            venda.getItens().add(item);

            BigDecimal subtotal = medicamento.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()));
            total = total.add(subtotal);
        }

        venda.setTotal(total);

        Venda response = vendaDAO.save(venda);
        return new VendaResponseDTO(response);
    }

    public VendaResponseDTO getById(Long id) {
        Venda venda = vendaDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Venda não encontrada"));
        return new VendaResponseDTO(venda);
    }

    public List<VendaResponseDTO> getAll() {
        return vendaDAO.findAll()
                .stream()
                .map(VendaResponseDTO::new)
                .toList();
    }

    @Transactional
    public VendaResponseDTO update(Long id,VendaRequestDTO dto){

        Venda vendaEx = vendaDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Venda não encontrada"));

        Cliente cliente = clienteDAO.findById(dto.getCliente_id())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Filial filial = filialDAO.findById(dto.getFilial_id())
                .orElseThrow(() -> new IllegalArgumentException("Filial não encontrada"));

        vendaEx.getItens().clear();

        BigDecimal total = BigDecimal.ZERO;
        List<MedicamentoVenda> novosItens = new ArrayList<>();

        for(MedicamentoVendaRequestDTO itemDto : dto.getItens()){
            Medicamento medicamento = medicamentoDAO.findById(itemDto.getMedicamentoId())
                    .orElseThrow(() -> new IllegalArgumentException("Medicamento não encontrado"));

            MedicamentoVenda item = new MedicamentoVenda();
            item.setVenda(vendaEx);
            item.setMedicamento(medicamento);
            item.setQuantidade(itemDto.getQuantidade());
            item.setValorUnitario(medicamento.getPreco());
            item.setReceita(itemDto.getReceita());

            novosItens.add(item);

            BigDecimal subtotal = medicamento.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()));
            total = total.add(subtotal);
        }

        vendaEx.setCliente(cliente);
        vendaEx.setFilial(filial);
        vendaEx.setDataHora(LocalDateTime.now());
        vendaEx.setItens(novosItens);
        vendaEx.setTotal(total);

        vendaEx.setTotal(total);

        Venda response = vendaDAO.save(vendaEx);
        return new VendaResponseDTO(response);
    }

    public void delete(Long id){
        if(!vendaDAO.existsById(id)){
            throw new EntityNotFoundException("Venda não encontrada com id: " + id);
        }
        vendaDAO.deleteById(id);
    }
}
