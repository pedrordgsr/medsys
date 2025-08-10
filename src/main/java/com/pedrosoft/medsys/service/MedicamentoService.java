package com.pedrosoft.medsys.service;

import com.pedrosoft.medsys.model.dto.request.FilialRequestDTO;
import com.pedrosoft.medsys.model.dto.request.MedicamentoRequestDTO;
import com.pedrosoft.medsys.model.dto.response.FilialResponseDTO;
import com.pedrosoft.medsys.model.dto.response.MedicamentoResponseDTO;
import com.pedrosoft.medsys.model.entities.Filial;
import com.pedrosoft.medsys.model.entities.Medicamento;
import com.pedrosoft.medsys.model.repository.FilialDAO;
import com.pedrosoft.medsys.model.repository.MedicamentoDAO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicamentoService {

    private final MedicamentoDAO medicamentoDAO;
    private final FilialDAO filialDAO;

    @Autowired
    public MedicamentoService(MedicamentoDAO medicamentoDAO, FilialDAO filialDAO){
        this.medicamentoDAO = medicamentoDAO;
        this.filialDAO = filialDAO;
    }

    public MedicamentoResponseDTO create(MedicamentoRequestDTO dto){

        Filial filial = filialDAO.findById(dto.getFilialId())
                .orElseThrow(() -> new EntityNotFoundException("Informe uma filial existente!"));

        Medicamento medicamento = new Medicamento();
        medicamento.setNome(dto.getNome());
        medicamento.setPreco(dto.getPreco());
        medicamento.setTipo(dto.getTipo());
        medicamento.setFilial(filial);
        medicamento.setEstoque(0);

        medicamentoDAO.save(medicamento);
        return new MedicamentoResponseDTO(medicamento);
    }

    public List<MedicamentoResponseDTO> getAll(){
        return medicamentoDAO.findAll()
                .stream()
                .map(MedicamentoResponseDTO::new)
                .collect(Collectors.toList());
    }

    public MedicamentoResponseDTO getById(Long id){
        Medicamento medicamento = medicamentoDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medicamento não encontrado com id: " + id));
        return new MedicamentoResponseDTO(medicamento);
    }

    public MedicamentoResponseDTO update(Long id, MedicamentoRequestDTO dto){

        Medicamento medicamento = medicamentoDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medicamento não encontrado com id: " + id));

        Filial filial = filialDAO.findById(dto.getFilialId())
                .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada com id: " + id));

        medicamento.setNome(dto.getNome());
        medicamento.setPreco(dto.getPreco());
        medicamento.setTipo(dto.getTipo());
        medicamento.setFilial(filial);

        medicamentoDAO.save(medicamento);
        return new MedicamentoResponseDTO(medicamento);
    }

    public void delete(Long id){
        if(!medicamentoDAO.existsById(id)){
            throw new EntityNotFoundException("Medicamento não encontrado com id: " + id);
        }
        medicamentoDAO.deleteById(id);
    }

    public String atualizaEstoque(Long id, int estoque){

        Medicamento medicamento = medicamentoDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Medicamento não encontrado com id: " + id));

        if (estoque < 0){
            throw new IllegalArgumentException("Estoque negativo! Informe um valor de estoque positivo.");
        }

        medicamento.setEstoque(estoque);

        medicamentoDAO.save(medicamento);

        return "Estoque atualizado, quantidade atual do medicamento " + medicamento.getNome() + " é de: " + medicamento.getEstoque() + " unidades.";
    }
}
