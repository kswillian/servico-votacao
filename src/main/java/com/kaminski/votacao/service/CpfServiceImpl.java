package com.kaminski.votacao.service;

import com.kaminski.votacao.model.dto.RetornoCpfDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CpfServiceImpl{

    private CpfService cpfService;

    public RetornoCpfDto validarCPF(String cpf) {
        return cpfService.validarCPF(cpf);
    }

}
