package com.kaminski.votacao.service;

import com.kaminski.votacao.model.documents.Associado;
import com.kaminski.votacao.model.form.AssociadoForm;
import com.kaminski.votacao.repository.AssociadoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AssociadoServiceImpl implements AssociadoService{

    private AssociadoRepository associadoRepository;

    @Override
    public Associado cadastrar(AssociadoForm associadoForm) {

        Associado associado = Associado.builder()
                .nome(associadoForm.getNome())
                .cpf(associadoForm.getCpf())
                .build();

        log.info("REGISTRANDO ASSOCIADO");
        associadoRepository.save(associado);

        return associado;

    }

    @Override
    public List<Associado> listarTodos() {
        return associadoRepository.findAll();
    }

    @Override
    public Associado buscarPorId(String id) {
        return associadoRepository.findById(id).get();
    }

    @Override
    public Boolean associadoExistePorCpf(String cpf) {
        return associadoRepository.existsByCpf(cpf);
    }

}
