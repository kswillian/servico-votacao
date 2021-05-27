package com.kaminski.votacao.repository;

import com.kaminski.votacao.model.documents.Pauta;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PautaRepository extends MongoRepository<Pauta, String> {
}
