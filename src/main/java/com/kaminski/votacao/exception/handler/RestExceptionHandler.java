package com.kaminski.votacao.exception.handler;

import com.kaminski.votacao.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String RESOURCE_NOT_FOUND = "Resource Not Found";
    private static final String BAD_REQUEST = "Bad Resquest";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var camposComErro = ex.getBindingResult().getFieldErrors();

        String fields = camposComErro.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String messages = camposComErro.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));

        Validacao validationErrorDetails = Validacao.builder()
                .titulo("Field Validation Error")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .detalhes("Field Validation Error")
                .timestamp(LocalDateTime.now())
                .mensagem(ex.getClass().getName())
                .campo(fields)
                .mensagemCampo(messages)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrorDetails);
    }

    @ExceptionHandler(AssociadoPossuiCadastroException.class)
    public ResponseEntity<AssociadoPossuiCadastroException> handleAssociadoPossuiCadastroException(AssociadoPossuiCadastroException associadoPossuiCadastroException){
        return new ResponseEntity(
                Excecao.builder()
                        .titulo(BAD_REQUEST)
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
                        .titulo(BAD_REQUEST)
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
                        .titulo(BAD_REQUEST)
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
                        .titulo(RESOURCE_NOT_FOUND)
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .detalhes(recursoNaoEncontradoException.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .mensagem(recursoNaoEncontradoException.getMessage())
                        .build(), HttpStatus.NOT_FOUND

        );
    }

}
