package com.kaminski.votacao.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ResultadoPautaDto {

    @ApiModelProperty(value = "Titulo da pauta da votação.")
    private String titulo;

    @ApiModelProperty(value = "Descrição da pauta da votação.")
    private String descricao;

    @ApiModelProperty(value = "Quantidade de votos positivos.")
    private Integer quantidadeVotosSim;

    @ApiModelProperty(value = "Quantidade de votos negativos.")
    private Integer quantidadeVotosNao;

}
