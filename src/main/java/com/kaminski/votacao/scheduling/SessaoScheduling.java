package com.kaminski.votacao.scheduling;

import com.kaminski.votacao.model.documents.Sessao;
import com.kaminski.votacao.model.dto.ResultadoPautaDto;
import com.kaminski.votacao.producer.ProdutorResultadoVotacao;
import com.kaminski.votacao.service.SessaoService;
import com.kaminski.votacao.service.VotoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class SessaoScheduling {

    private SessaoService sessaoService;
    private VotoService votoService;
    private ProdutorResultadoVotacao produtorResultadoVotacao;

    @Scheduled(cron = "15 * * * * *")
    public void verificarSessoesEncerradasParaDivulgar(){
        log.info("Verificando sessões para divulgar...");

        var sessoes = sessaoService.listarSessoesEncerradasNaoDivulgadas();
        sessoes.forEach(sessao -> {
            var resultadoPautaDto = votoService.buscarTotalVotosPorPauta(sessao.getPautaId());
            sessaoService.divulgar(sessao);
            produtorResultadoVotacao.produzirMensagem(resultadoPautaDto);
        });
    }

    @Scheduled(cron = "*/1 * * * * *")
    public void verificarSessoesParaEncerrar(){

        log.info("Verificando sessões para encerrar...");

        var sessoes = sessaoService.listarSessoesAbertas();
        sessoes.forEach(sessao -> {
            log.info(String.format("Encerrando sessão {s} " + sessao.getId()));
            sessaoService.encerrar(sessao);
        });

    }

}
