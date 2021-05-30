package com.kaminski.votacao.model.documents;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Sessao {

    @Id
    @ApiModelProperty(value = "Identificar único da sessão.")
    private String id;

    @ApiModelProperty(value = "Data e hora de inicio da sessão.")
    private LocalDateTime dataHoraInicio;

    @ApiModelProperty(value = "Data e hora de termino da sessão.")
    private LocalDateTime dataHoraFim;

    @ApiModelProperty(value = "Identificar único da pauta.")
    private String pautaId;

    @ApiModelProperty(value = "Status da sessão (TRUE = Em andamento / FALSE = Encerrada).")
    private Boolean status;

    @ApiModelProperty(value = "Divulgação dos resultados da sessão (TRUE = Sim / FALSE = Não).")
    private Boolean divulgada;

}
