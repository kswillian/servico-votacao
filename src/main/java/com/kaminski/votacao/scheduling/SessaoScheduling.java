package com.kaminski.votacao.scheduling;

import com.kaminski.votacao.service.SessaoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class SessaoScheduling {

    private SessaoService sessaoService;

    @Scheduled(cron = "15 * * * * *")
    public void verificarSessoesEncerradasParaDivulgar(){
        log.info("Verificando sessões para divulgar...");
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void verificarSessoesParaEncerrar(){

        log.info("Verificando sessões para encerrar...");

        var sessoes = sessaoService.listarSessoesAbertas();
        sessoes.forEach(sessao -> {
            log.info(String.format("Encerrando sessão {s} " + sessao.getId()));
            sessaoService.encerrar(sessao);
        });

    }

}
