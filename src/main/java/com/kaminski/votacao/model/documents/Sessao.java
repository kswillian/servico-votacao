package com.kaminski.votacao.model.documents;

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
    private String id;

    private LocalDateTime dataHoraInicio;

    private LocalDateTime dataHoraFim;

    private String pautaId;

    private Boolean status;

    private Boolean divulgada;

}
