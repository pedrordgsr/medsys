package com.pedrosoft.medsys.controller;

import com.pedrosoft.medsys.model.dto.request.ClienteRequestDTO;
import com.pedrosoft.medsys.model.dto.response.ClienteResponseDTO;
import com.pedrosoft.medsys.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService ClienteService) {
        this.clienteService = ClienteService;
    }

    @GetMapping
    public ResponseEntity<?> getAll (){
        try{
            return ResponseEntity.ok(clienteService.getAll());
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById (@RequestParam Long id){
        try{
            return ResponseEntity.ok(clienteService.getById(id));
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody ClienteRequestDTO clienteRequestDTO){
        try{
            ClienteResponseDTO response = clienteService.create(clienteRequestDTO);
            return ResponseEntity.ok(response);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@Valid @RequestBody ClienteRequestDTO dto, @RequestParam Long id){
        try{
            ClienteResponseDTO response = clienteService.update(id, dto);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete (@RequestParam Long id){
        try{
            clienteService.delete(id);
            return ResponseEntity.ok("Cliente id:" + id + " deletado!");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
