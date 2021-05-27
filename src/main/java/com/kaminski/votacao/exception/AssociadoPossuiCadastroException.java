package com.kaminski.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AssociadoPossuiCadastroException extends RuntimeException{

    public AssociadoPossuiCadastroException(String message) {
        super(message);
    }

}
