package com.kaminski.votacao.model.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class AssociadoForm {

    @NotEmpty
    private String nome;

    @NotEmpty
    private String cpf;

}
