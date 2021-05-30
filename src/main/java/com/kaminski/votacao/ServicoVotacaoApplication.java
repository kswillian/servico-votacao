package com.kaminski.votacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@EnableScheduling
@SpringBootApplication
public class ServicoVotacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicoVotacaoApplication.class, args);
	}

}
