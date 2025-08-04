package com.pedrosoft.medsys.service;

import com.pedrosoft.medsys.model.dto.request.FilialRequestDTO;
import com.pedrosoft.medsys.model.dto.response.FilialResponseDTO;
import com.pedrosoft.medsys.model.entities.Filial;
import com.pedrosoft.medsys.model.repository.FilialDAO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilialService {

    private final FilialDAO filialDAO;

    @Autowired
    public FilialService (FilialDAO filialDAO){this.filialDAO = filialDAO;}

    public FilialResponseDTO create(FilialRequestDTO dto){

        Filial filial = new Filial();
        filial.setEndereco(dto.getEndereco());
        filial.setTelefone(dto.getTelefone());

        Filial response = filialDAO.save(filial);
        return new FilialResponseDTO(filial);
    }

    public List<FilialResponseDTO> getAll(){
        return filialDAO.findAll()
                .stream()
                .map(FilialResponseDTO::new)
                .collect(Collectors.toList());
    }

    public FilialResponseDTO getById(Long id){
        Filial filial = filialDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com id: " + id));
        return new FilialResponseDTO(filial);
    }

    public FilialResponseDTO update(Long id, FilialRequestDTO dto){

        Filial filial = filialDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada com id: " + id));

        filial.setEndereco(dto.getEndereco());
        filial.setTelefone(dto.getTelefone());

        Filial response = filialDAO.save(filial);
        return new FilialResponseDTO(response);
    }

    public void delete(Long id){
        if(!filialDAO.existsById(id)){
            throw new EntityNotFoundException("Filial não encontrada com id: " + id);
        }
        filialDAO.deleteById(id);
    }
}
