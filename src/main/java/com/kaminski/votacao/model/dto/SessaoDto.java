package com.kaminski.votacao.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kaminski.votacao.model.documents.Sessao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class SessaoDto {

    private String id;

    private LocalDateTime dataHoraInicio;

    private LocalDateTime dataHoraFim;

    private String pauta;

    private Boolean status;

    public static SessaoDto converterDocumentoParaDto(Sessao sessao){
        return SessaoDto.builder()
                .id(sessao.getId())
                .dataHoraInicio(sessao.getDataHoraInicio())
                .dataHoraFim(sessao.getDataHoraFim())
                .pauta(sessao.getPautaId())
                .status(sessao.getStatus())
                .build();
    }
}
