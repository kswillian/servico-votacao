package com.kaminski.votacao.service;

import com.kaminski.votacao.model.documents.Voto;
import com.kaminski.votacao.model.form.VotoForm;

import java.util.List;

public interface VotoService {

    Voto votar(VotoForm votoForm);

    List<Voto> buscarTotalVotosPorSessao(String idSessao);

}
