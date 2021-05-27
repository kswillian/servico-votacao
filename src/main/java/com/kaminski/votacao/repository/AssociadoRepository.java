package com.kaminski.votacao.repository;

import com.kaminski.votacao.model.documents.Associado;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AssociadoRepository extends MongoRepository<Associado, String> {

    Boolean existsByCpf(String cpf);

}
