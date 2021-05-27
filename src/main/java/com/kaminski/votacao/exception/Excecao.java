package com.kaminski.votacao.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class Excecao {

    protected String titulo;
    protected Integer statusCode;
    protected String detalhes;
    protected LocalDateTime timestamp;
    protected String mensagem;

}
