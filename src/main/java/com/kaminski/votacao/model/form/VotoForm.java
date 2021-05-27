package com.kaminski.votacao.model.form;

import com.kaminski.votacao.model.documents.OpcaoVoto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class VotoForm {

    @NotEmpty
    private OpcaoVoto opcaoVoto;

    @NotEmpty
    private String idAssociado;

    @NotEmpty
    private String idSessao;

}
