package com.kaminski.votacao.repository;

import com.kaminski.votacao.model.enuns.OpcaoVoto;
import com.kaminski.votacao.model.documents.Voto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VotoRepository extends MongoRepository<Voto, String> {

    Boolean existsByAssociadoIdAndPautaId(String associadoId, String pautaId);

    List<Voto> findAllByPautaId(String idPauta);

    Integer countByPautaIdAndOpcaoVoto(String idPauta, OpcaoVoto opcaoVoto);

}
