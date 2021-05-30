package com.kaminski.votacao.controller;

import com.kaminski.votacao.model.documents.Sessao;
import com.kaminski.votacao.model.dto.SessaoDto;
import com.kaminski.votacao.model.form.SessaoForm;
import com.kaminski.votacao.service.SessaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/sessoes")
public class SessaoController {

    private SessaoService sessaoService;

    @PostMapping("/abrir")
    public ResponseEntity<SessaoDto> abrir(@RequestBody @Valid SessaoForm sessaoForm){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sessaoService.abrir(sessaoForm));
    }

    @GetMapping
    public ResponseEntity<List<Sessao>> listarTodos(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sessaoService.listarTodos());
    }

}
