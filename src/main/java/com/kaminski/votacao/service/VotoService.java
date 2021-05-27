package com.kaminski.votacao.service;

import com.kaminski.votacao.model.documents.Voto;
import com.kaminski.votacao.model.dto.ResultadoPautaDto;
import com.kaminski.votacao.model.form.VotoForm;

public interface VotoService {

    Voto votar(VotoForm votoForm);

    ResultadoPautaDto buscarTotalVotosPorPauta(String idPauta);

}
