package com.kaminski.votacao.controller;

import com.kaminski.votacao.model.documents.Pauta;
import com.kaminski.votacao.model.form.PautaForm;
import com.kaminski.votacao.service.PautaService;
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
@RequestMapping("api/v1/pautas")
@Api(tags = "Pauta (Pautas)")
public class PautaController {

    private PautaService pautaService;

    @PostMapping
    @ApiOperation(value = "Realizar registro de uma nova pauta.")
    public ResponseEntity<Pauta> cadastrar(@RequestBody @Valid PautaForm pautaForm){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pautaService.cadastrar(pautaForm));
    }

    @GetMapping
    @ApiOperation(value = "Listar todas as pautas registradas.")
    public ResponseEntity<List<Pauta>> listarTodos(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pautaService.listarTodos());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Listar pauta através do seu identificador único.")
    public ResponseEntity<Pauta> listarPorId(@PathVariable String id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pautaService.buscarPorId(id));
    }

}
