package com.pedrosoft.medsys.controller;

import com.pedrosoft.medsys.model.dto.request.FilialRequestDTO;
import com.pedrosoft.medsys.model.dto.request.MedicamentoRequestDTO;
import com.pedrosoft.medsys.model.dto.response.FilialResponseDTO;
import com.pedrosoft.medsys.model.dto.response.MedicamentoResponseDTO;
import com.pedrosoft.medsys.service.MedicamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicamento")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;

    @Autowired
    public MedicamentoController(MedicamentoService medicamentoService){this.medicamentoService = medicamentoService;}

    @GetMapping
    public ResponseEntity<?> getAll (){
        try{
            return ResponseEntity.ok(medicamentoService.getAll());
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById (@PathVariable Long id){
        try{
            return ResponseEntity.ok(medicamentoService.getById(id));
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody MedicamentoRequestDTO dto){
        try{
            MedicamentoResponseDTO response = medicamentoService.create(dto);
            return ResponseEntity.ok(response);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@Valid @RequestBody MedicamentoRequestDTO dto, @PathVariable Long id){
        try{
            MedicamentoResponseDTO response = medicamentoService.update(id, dto);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/estoque/{id}")
    public ResponseEntity<?> estoque (@PathVariable Long id, @Valid @RequestBody int estoque){
        try{
            String response = medicamentoService.atualizaEstoque(id, estoque);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        try{
            medicamentoService.delete(id);
            return ResponseEntity.ok("Medicamento id:" + id + " deletado!");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
