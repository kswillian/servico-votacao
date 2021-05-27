package com.kaminski.votacao.service;

import com.kaminski.votacao.model.documents.Pauta;
import com.kaminski.votacao.model.form.PautaForm;
import com.kaminski.votacao.repository.PautaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PautaServiceImpl implements PautaService {

    private PautaRepository pautaRepository;

    @Override
    public Pauta cadastrar(PautaForm pautaForm) {

        Pauta pauta = Pauta.builder()
                .titulo(pautaForm.getTitulo())
                .descricao(pautaForm.getDescricao())
                .build();

        return pautaRepository.save(pauta);

    }

    @Override
    public List<Pauta> listarTodos() {
        return pautaRepository.findAll();
    }

    @Override
    public Pauta buscarPorId(String id) {
        return pautaRepository.findById(id).get();
    }

}
