package com.kaminski.votacao.producer;

import com.kaminski.votacao.model.dto.ResultadoPautaDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ProdutorResultadoVotacao {

    private RabbitTemplate rabbitTemplate;
    private Queue queue;

    public void produzirMensagem(ResultadoPautaDto resultadoPautaDto){
        log.info(String.format("Produzindo mensagem com os resultados da sessão na fila '%s' do RabbitMQ... ", queue.getName()));
        rabbitTemplate.convertAndSend(queue.getName(), resultadoPautaDto.toString());
    }
}
