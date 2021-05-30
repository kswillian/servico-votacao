package com.kaminski.votacao.model.dto;

import com.kaminski.votacao.model.documents.Sessao;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class SessaoDto {

    @ApiModelProperty(value = "Identificar único da sessão.")
    private String id;

    @ApiModelProperty(value = "Data e hora de inicio da sessão.")
    private LocalDateTime dataHoraInicio;

    @ApiModelProperty(value = "Data e hora de termino da sessão.")
    private LocalDateTime dataHoraFim;

    @ApiModelProperty(value = "Pauta relacionada a sessão.")
    private String pauta;

    @ApiModelProperty(value = "Status da sessão (TRUE = Em andamento / FALSE = Encerrada).")
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
