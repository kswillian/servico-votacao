package com.kaminski.votacao.controller;

import com.kaminski.votacao.model.documents.Associado;
import com.kaminski.votacao.model.form.AssociadoForm;
import com.kaminski.votacao.service.AssociadoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/associados")
public class AssociadoController {

    private AssociadoService associadoService;

    @PostMapping
    public ResponseEntity<Associado> cadastrar(@RequestBody @Valid AssociadoForm associadoForm){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(associadoService.cadastrar(associadoForm));
    }

    @GetMapping
    public ResponseEntity<List<Associado>> listarTodos(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(associadoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Associado> listarPorId(@PathVariable String id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(associadoService.buscarPorId(id));
    }

}
