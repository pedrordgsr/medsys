package com.pedrosoft.medsys.controller;

import com.pedrosoft.medsys.model.dto.request.ClienteRequestDTO;
import com.pedrosoft.medsys.model.dto.request.FilialRequestDTO;
import com.pedrosoft.medsys.model.dto.response.ClienteResponseDTO;
import com.pedrosoft.medsys.model.dto.response.FilialResponseDTO;
import com.pedrosoft.medsys.service.ClienteService;
import com.pedrosoft.medsys.service.FilialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/filial")
public class FilialController {

    private final FilialService filialService;

    @Autowired
    public FilialController(FilialService filialService) {
        this.filialService = filialService;
    }

    @GetMapping
    public ResponseEntity<?> getAll (){
        try{
            return ResponseEntity.ok(filialService.getAll());
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById (@RequestParam Long id){
        try{
            return ResponseEntity.ok(filialService.getById(id));
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody FilialRequestDTO dto){
        try{
            FilialResponseDTO response = filialService.create(dto);
            return ResponseEntity.ok(response);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@Valid @RequestBody FilialRequestDTO dto, @RequestParam Long id){
        try{
            FilialResponseDTO response = filialService.update(id, dto);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete (@RequestParam Long id){
        try{
            filialService.delete(id);
            return ResponseEntity.ok("Filial id:" + id + " deletada!");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
