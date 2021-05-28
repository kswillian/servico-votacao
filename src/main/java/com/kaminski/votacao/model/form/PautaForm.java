package com.kaminski.votacao.model.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class PautaForm {

    @NotEmpty
    private String titulo;

    @NotEmpty
    private String descricao;

}
