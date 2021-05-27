package com.kaminski.votacao.service;

import com.kaminski.votacao.model.documents.Pauta;
import com.kaminski.votacao.model.form.PautaForm;

import java.util.List;

public interface PautaService {

    Pauta cadastrar(PautaForm pautaForm);

    List<Pauta> listarTodos();

    Pauta buscarPorId(String id);

}
