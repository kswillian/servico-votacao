package com.kaminski.votacao.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter @SuperBuilder
public class Validacao extends Excecao{

    private String campo;
    private String mensagemCampo;

}
