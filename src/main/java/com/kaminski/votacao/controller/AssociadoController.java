package com.kaminski.votacao.controller;

import com.kaminski.votacao.model.documents.Associado;
import com.kaminski.votacao.model.form.AssociadoForm;
import com.kaminski.votacao.service.AssociadoService;
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
@RequestMapping("api/v1/associados")
@Api(tags = "Associado (Associados)")
public class AssociadoController {

    private AssociadoService associadoService;

    @PostMapping
    @ApiOperation(value = "Realizar registro de um novo associado.")
    public ResponseEntity<Associado> cadastrar(@RequestBody @Valid AssociadoForm associadoForm){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(associadoService.cadastrar(associadoForm));
    }

    @GetMapping
    @ApiOperation(value = "Listar todos os associados registrados.")
    public ResponseEntity<List<Associado>> listarTodos(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(associadoService.listarTodos());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar associado através do seu identificador único.")
    public ResponseEntity<Associado> listarPorId(@PathVariable String id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(associadoService.buscarPorId(id));
    }

}
