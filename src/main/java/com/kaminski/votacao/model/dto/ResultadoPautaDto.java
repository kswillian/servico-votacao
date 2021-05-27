package com.kaminski.votacao.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ResultadoPautaDto {

    private String titulo;

    private String descricao;

    private Integer quantidadeVotosSim;

    private Integer quantidadeVotosNao;

}
