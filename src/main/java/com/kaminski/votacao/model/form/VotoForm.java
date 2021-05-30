package com.kaminski.votacao.model.form;

import com.kaminski.votacao.model.documents.OpcaoVoto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class VotoForm {

    @NotNull
    @ApiModelProperty(value = "Opção de votos (SIM / NÃO).")
    private OpcaoVoto opcaoVoto;

    @NotEmpty
    @ApiModelProperty(value = "Identificar único do associado.")
    private String idAssociado;

    @NotEmpty
    @ApiModelProperty(value = "Identificar único da pauta.")
    private String idPauta;

    @NotEmpty
    @ApiModelProperty(value = "Identificar único da sessão.")
    private String idSessao;

}
