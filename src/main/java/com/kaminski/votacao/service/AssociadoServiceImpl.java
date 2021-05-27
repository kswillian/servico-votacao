package com.kaminski.votacao.service;

import com.kaminski.votacao.exception.AssociadoPossuiCadastroException;
import com.kaminski.votacao.model.documents.Associado;
import com.kaminski.votacao.model.form.AssociadoForm;
import com.kaminski.votacao.repository.AssociadoRepository;
import com.kaminski.votacao.util.Validacao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AssociadoServiceImpl implements AssociadoService{

    private AssociadoRepository associadoRepository;
    private Validacao validacao;

    @Override
    public Associado cadastrar(AssociadoForm associadoForm) {

        validacao.verificarSeCpfEhValido(associadoForm.getCpf());
        validacao.verificarSeCpfJaPossuiCadastro(associadoForm.getCpf());

        Associado associado = Associado.builder()
                .nome(associadoForm.getNome())
                .cpf(associadoForm.getCpf())
                .build();

        return associadoRepository.save(associado);

    }

    @Override
    public List<Associado> listarTodos() {
        return associadoRepository.findAll();
    }

    @Override
    public Associado buscarPorId(String id) {
        return associadoRepository.findById(id).get();
    }

}
