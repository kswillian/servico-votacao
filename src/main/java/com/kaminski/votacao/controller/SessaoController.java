package com.kaminski.votacao.controller;

import com.kaminski.votacao.model.documents.Sessao;
import com.kaminski.votacao.model.dto.SessaoDto;
import com.kaminski.votacao.model.form.SessaoForm;
import com.kaminski.votacao.service.SessaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/sessoes")
@Api(tags = "Sessão (Sessões)")
public class SessaoController {

    private SessaoService sessaoService;

    @PostMapping("/abrir")
    @ApiOperation(value = "Realizar abertura de uma nova sessão de votação.")
    public ResponseEntity<SessaoDto> abrir(@RequestBody @Valid SessaoForm sessaoForm){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sessaoService.abrir(sessaoForm));
    }

    @GetMapping
    @ApiOperation(value = "Listar todas as sessões registradas.")
    public ResponseEntity<List<Sessao>> listarTodos(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(sessaoService.listarTodos());
    }

}
