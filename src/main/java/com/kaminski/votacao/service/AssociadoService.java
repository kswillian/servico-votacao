package com.kaminski.votacao.service;

import com.kaminski.votacao.model.documents.Associado;
import com.kaminski.votacao.model.form.AssociadoForm;

import java.util.List;

public interface AssociadoService {

    Associado cadastrar(AssociadoForm associadoForm);

    List<Associado> listarTodos();

    Associado buscarPorId(String id);

}
