package com.kaminski.votacao.model.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class SessaoForm {

    @NotEmpty
    @ApiModelProperty(value = "Identificar único da pauta.")
    private String pautaId;

    @ApiModelProperty(value = "Tempo de duração da sessão (Minutos).")
    private Integer tempoDuracao;

}
