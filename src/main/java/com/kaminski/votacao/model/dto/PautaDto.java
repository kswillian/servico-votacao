package com.kaminski.votacao.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class PautaDto {

    @ApiModelProperty(value = "Identificador único da pauta.")
    private String id;

    @ApiModelProperty(value = "Título da pauta.")
    private String titulo;

    @ApiModelProperty(value = "Descrição da pauta.")
    private String descricao;

}
