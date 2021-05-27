package com.kaminski.votacao.model.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AssociadoForm {

    @NotEmpty
    private String nome;

    @NotEmpty
    private String cpf;

}
