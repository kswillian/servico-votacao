package com.kaminski.votacao.controller;

import com.kaminski.votacao.model.documents.Pauta;
import com.kaminski.votacao.model.form.PautaForm;
import com.kaminski.votacao.service.PautaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/pautas")
public class PautaController {

    private PautaService pautaService;

    @PostMapping
    public ResponseEntity<Pauta> cadastrar(@RequestBody @Valid PautaForm pautaForm){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pautaService.cadastrar(pautaForm));
    }

    @GetMapping
    public ResponseEntity<List<Pauta>> listarTodos(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pautaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pauta> listarPorId(@PathVariable String id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pautaService.buscarPorId(id));
    }

}
