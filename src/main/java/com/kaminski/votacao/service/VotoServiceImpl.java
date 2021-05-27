package com.kaminski.votacao.service;

import com.kaminski.votacao.model.documents.Voto;
import com.kaminski.votacao.model.form.VotoForm;
import com.kaminski.votacao.repository.VotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VotoServiceImpl implements VotoService{

    private VotoRepository votoRepository;

    @Override
    public Voto votar(VotoForm votoForm) {

        Voto voto = Voto.builder()
                .idSessao(votoForm.getIdSessao())
                .opcaoVoto(votoForm.getOpcaoVoto())
                .idAssociado(votoForm.getIdAssociado())
                .build();

        return votoRepository.save(voto);

    }

    @Override
    public List<Voto> buscarTotalVotosPorSessao(String idSessao) {
        return null;
    }
}
