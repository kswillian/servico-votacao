package com.kaminski.votacao.model.documents;

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
    private String id;

    private OpcaoVoto opcaoVoto;

    private String associadoId;

    private String pautaId;

    private String sessaoId;

}
