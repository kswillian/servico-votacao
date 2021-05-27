package com.kaminski.votacao.model.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
public class SessaoForm {

    @NotEmpty
    private String pautaId;

    @Positive
    private Integer tempoDuracao;

}
