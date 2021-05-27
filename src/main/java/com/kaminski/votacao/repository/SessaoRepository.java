package com.kaminski.votacao.repository;

import com.kaminski.votacao.model.documents.Sessao;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SessaoRepository extends MongoRepository<Sessao, String> {

    List<Sessao> findByStatusTrue();

    List<Sessao> findByStatusFalse();

    List<Sessao> findByStatusFalseAndDivulgadaFalse();

}
