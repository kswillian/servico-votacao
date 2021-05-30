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
public class Associado {

    @Id
    @ApiModelProperty(value = "Identificador único do associado.")
    private String id;

    @ApiModelProperty(value = "Nome do associado.")
    private String cpf;

    @ApiModelProperty(value = "CPF do associado.")
    private String nome;

}
