package com.kaminski.votacao.model.documents;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Pauta {

    @Id
    @ApiModelProperty(value = "Identificador único da pauta.")
    private String id;

    @ApiModelProperty(value = "Título da pauta.")
    private String titulo;

    @ApiModelProperty(value = "Descrição da pauta.")
    private String descricao;

}
