package com.kaminski.votacao.model.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class AssociadoForm {

    @NotEmpty
    @ApiModelProperty(value = "Nome do associado.")
    private String nome;

    @NotEmpty
    @ApiModelProperty(value = "CPF do associado.")
    private String cpf;

}
