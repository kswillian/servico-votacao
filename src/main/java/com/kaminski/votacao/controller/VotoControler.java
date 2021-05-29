package com.kaminski.votacao.controller;

import com.kaminski.votacao.model.documents.Voto;
import com.kaminski.votacao.model.form.VotoForm;
import com.kaminski.votacao.service.VotoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/votos")
public class VotoControler {

    private VotoService votoService;

    @PostMapping
    public ResponseEntity<Voto> votar(@RequestBody @Valid VotoForm votoForm){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(votoService.votar(votoForm));
    }

}
