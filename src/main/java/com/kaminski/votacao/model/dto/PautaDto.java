package com.kaminski.votacao.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class PautaDto {

    private String id;

    private String titulo;

    private String descricao;

}
