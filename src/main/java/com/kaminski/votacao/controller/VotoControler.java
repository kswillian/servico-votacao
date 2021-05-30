package com.kaminski.votacao.controller;

import com.kaminski.votacao.model.documents.Voto;
import com.kaminski.votacao.model.form.VotoForm;
import com.kaminski.votacao.service.VotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/votos")
@Api(tags = "Voto (Votos)")
public class VotoControler {

    private VotoService votoService;

    @PostMapping
    @ApiOperation(value = "Realizar votação")
    public ResponseEntity<Voto> votar(@RequestBody @Valid VotoForm votoForm){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(votoService.votar(votoForm));
    }

}
