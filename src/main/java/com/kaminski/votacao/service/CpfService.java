package com.kaminski.votacao.service;

import com.kaminski.votacao.model.dto.RetornoCpfDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "${users.api.url}", name = "cpfDto")
public interface CpfService {

    @RequestMapping(value = "/{cpf}", method = RequestMethod.GET)
    RetornoCpfDto validarCPF(@PathVariable String cpf);

}
