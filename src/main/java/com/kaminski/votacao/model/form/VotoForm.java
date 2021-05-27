package com.kaminski.votacao.model.form;

import com.kaminski.votacao.model.documents.OpcaoVoto;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class VotoForm {

    @NotNull
    private OpcaoVoto opcaoVoto;

    @NotEmpty
    private String idAssociado;

    @NotEmpty
    private String idPauta;

    @NotEmpty
    private String idSessao;

}
