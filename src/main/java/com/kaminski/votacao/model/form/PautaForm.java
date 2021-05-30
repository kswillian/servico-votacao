package com.kaminski.votacao.model.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class PautaForm {

    @NotEmpty
    @ApiModelProperty(value = "Título da pauta.")
    private String titulo;

    @NotEmpty
    @ApiModelProperty(value = "Descrição da pauta.")
    private String descricao;

}
