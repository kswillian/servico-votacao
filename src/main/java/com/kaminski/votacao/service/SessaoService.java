package com.kaminski.votacao.service;

import com.kaminski.votacao.model.documents.Sessao;
import com.kaminski.votacao.model.dto.SessaoDto;
import com.kaminski.votacao.model.form.SessaoForm;

import java.util.List;

public interface SessaoService {

    SessaoDto abrir(SessaoForm sessaoForm);

    Sessao encerrar(Sessao sessao);

    List<Sessao> listarTodos();

    List<Sessao> listarSessoesAbertas();

    List<Sessao> listarSessoesEncerradasNaoDivulgadas();

    Sessao buscarPorId(String id);

}
