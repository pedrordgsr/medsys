package com.pedrosoft.medsys.controller;

import com.pedrosoft.medsys.model.dto.request.MedicamentoRequestDTO;
import com.pedrosoft.medsys.model.dto.request.VendaRequestDTO;
import com.pedrosoft.medsys.model.dto.response.MedicamentoResponseDTO;
import com.pedrosoft.medsys.model.dto.response.VendaResponseDTO;
import com.pedrosoft.medsys.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/venda")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService){this.vendaService = vendaService;}

    @GetMapping
    public ResponseEntity<?> getAll (){
        try{
            return ResponseEntity.ok(vendaService.getAll());
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById (@PathVariable Long id){
        try{
            return ResponseEntity.ok(vendaService.getById(id));
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody VendaRequestDTO dto){
        try{
            VendaResponseDTO response = vendaService.create(dto);
            return ResponseEntity.ok(response);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@Valid @RequestBody VendaRequestDTO dto, @PathVariable Long id){
        try{
            VendaResponseDTO response = vendaService.update(id, dto);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        try{
            vendaService.delete(id);
            return ResponseEntity.ok("Venda id:" + id + " deletada!");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
