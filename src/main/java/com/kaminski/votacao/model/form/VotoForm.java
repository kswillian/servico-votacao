package com.kaminski.votacao.model.form;

import com.kaminski.votacao.model.documents.OpcaoVoto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
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
