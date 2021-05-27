package com.kaminski.votacao.exception.handler;

import com.kaminski.votacao.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AssociadoPossuiCadastroException.class)
    public ResponseEntity<AssociadoPossuiCadastroException> handleAssociadoPossuiCadastroException(AssociadoPossuiCadastroException associadoPossuiCadastroException){
        return new ResponseEntity(
                Excecao.builder()
                        .titulo("Bad Request")
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .detalhes(associadoPossuiCadastroException.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .mensagem(associadoPossuiCadastroException.getMessage())
                        .build(), HttpStatus.BAD_REQUEST

        );
    }

    @ExceptionHandler(AssociadoPossuiVoto.class)
    public ResponseEntity<AssociadoPossuiVoto> handleAssociadoPossuiVoto(AssociadoPossuiVoto associadoPossuiVoto){
        return new ResponseEntity(
                Excecao.builder()
                        .titulo("Bad Request")
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .detalhes(associadoPossuiVoto.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .mensagem(associadoPossuiVoto.getMessage())
                        .build(), HttpStatus.BAD_REQUEST

        );
    }

    @ExceptionHandler(SessaoEncerradaException.class)
    public ResponseEntity<AssociadoPossuiVoto> handleSessaoEncerradaException(SessaoEncerradaException sessaoEncerradaException){
        return new ResponseEntity(
                Excecao.builder()
                        .titulo("Bad Request")
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .detalhes(sessaoEncerradaException.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .mensagem(sessaoEncerradaException.getMessage())
                        .build(), HttpStatus.BAD_REQUEST

        );
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<RecursoNaoEncontradoException> handleSessaoEncerradaException(RecursoNaoEncontradoException recursoNaoEncontradoException){
        return new ResponseEntity(
                Excecao.builder()
                        .titulo("Not Found")
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .detalhes(recursoNaoEncontradoException.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .mensagem(recursoNaoEncontradoException.getMessage())
                        .build(), HttpStatus.NOT_FOUND

        );
    }

}
