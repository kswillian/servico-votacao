package com.kaminski.votacao.model.documents;

import com.kaminski.votacao.model.enuns.OpcaoVoto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Voto {

    @Id
    @ApiModelProperty(value = "Identificador único do voto.")
    private String id;

    @ApiModelProperty(value = "Opção de votos (SIM / NÃO).")
    private OpcaoVoto opcaoVoto;

    @ApiModelProperty(value = "Identificador único do associado.")
    private String associadoId;

    @ApiModelProperty(value = "Identificador único da pauta.")
    private String pautaId;

    @ApiModelProperty(value = "Identificador único da sessão.")
    private String sessaoId;

}
