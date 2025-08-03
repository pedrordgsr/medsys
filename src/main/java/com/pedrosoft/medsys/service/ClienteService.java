package com.pedrosoft.medsys.service;

import com.pedrosoft.medsys.model.dto.request.ClienteRequestDTO;
import com.pedrosoft.medsys.model.dto.response.ClienteResponseDTO;
import com.pedrosoft.medsys.model.entities.Cliente;
import com.pedrosoft.medsys.model.repository.ClienteDAO;
import com.pedrosoft.medsys.util.CpfValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {


    private final ClienteDAO clienteDAO;

    @Autowired
    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public ClienteResponseDTO create(ClienteRequestDTO dto){
        String cpf = dto.getCpf();

        if (!CpfValidator.isValid(cpf)) {
            throw new IllegalArgumentException("CPF inválido");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setCpf(cpf);

        Cliente response = clienteDAO.save(cliente);
        return new ClienteResponseDTO(response);
    }

    public List<ClienteResponseDTO> getAll(){
        return clienteDAO.findAll()
                .stream()
                .map(ClienteResponseDTO::new)
                .collect(Collectors.toList());
    }

    public ClienteResponseDTO getById(Long id){
        Cliente cliente = clienteDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com id: " + id));
        return new ClienteResponseDTO(cliente);
    }

    public ClienteResponseDTO update(Long id, ClienteRequestDTO dto){
        String cpf = dto.getCpf();

        if (!CpfValidator.isValid(cpf)) {
            throw new IllegalArgumentException("CPF inválido");
        }

        Cliente cliente = clienteDAO.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com id: " + id));

        cliente.setNome(dto.getNome());
        cliente.setCpf(cpf);

        Cliente response = clienteDAO.save(cliente);
        return new ClienteResponseDTO(response);
    }

    public void delete(Long id){
        if(!clienteDAO.existsById(id)){
            throw new EntityNotFoundException("Produto não encontrado com id: " + id);
        }
        clienteDAO.deleteById(id);
    }
}
