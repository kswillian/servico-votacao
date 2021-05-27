package com.kaminski.votacao.repository;

import com.kaminski.votacao.model.documents.Voto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VotoRepository extends MongoRepository<Voto, String> {
}
