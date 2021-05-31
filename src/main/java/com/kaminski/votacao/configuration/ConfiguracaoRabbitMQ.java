package com.kaminski.votacao.configuration;

import lombok.Getter;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ConfiguracaoRabbitMQ {

    @Value("${rabbitmq.queue}")
    private String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingKey;

    @Value("${rabbitmq.exchange}")
    private String queueName;

    @Bean
    public Queue queue() {
        return new Queue(queueName, true);
    }

}
