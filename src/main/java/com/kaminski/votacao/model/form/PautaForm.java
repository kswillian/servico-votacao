package com.kaminski.votacao.model.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PautaForm {

    @NotEmpty
    private String titulo;

    @NotEmpty
    private String descricao;

}
