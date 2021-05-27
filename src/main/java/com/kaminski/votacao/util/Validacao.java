package com.kaminski.votacao.util;

import com.kaminski.votacao.exception.RecursoNaoEncontradoException;
import com.kaminski.votacao.exception.AssociadoPossuiCadastroException;
import com.kaminski.votacao.exception.AssociadoPossuiVoto;
import com.kaminski.votacao.exception.SessaoEncerradaException;
import com.kaminski.votacao.model.documents.Associado;
import com.kaminski.votacao.model.documents.Pauta;
import com.kaminski.votacao.model.documents.Sessao;
import com.kaminski.votacao.repository.AssociadoRepository;
import com.kaminski.votacao.repository.PautaRepository;
import com.kaminski.votacao.repository.SessaoRepository;
import com.kaminski.votacao.repository.VotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class Validacao {

    private AssociadoRepository associadoRepository;
    private SessaoRepository sessaoRepository;
    private PautaRepository pautaRepository;
    private VotoRepository votoRepository;

    public void verificarSeCpfJaPossuiCadastro(String cpf){
        if(associadoRepository.existsByCpf(cpf))
            throw new AssociadoPossuiCadastroException(String.format("Já existe um associado cadastrado com o CPF: %s", cpf));
    }

    public void verificarSeCpfEhValido(String cpf) {
        // implementar
    }

    public void verificarSeAssociadoExiste(String idAssociado) {
        Optional<Associado> associado = associadoRepository.findById(idAssociado);
        if(associado.isEmpty())
            throw new RecursoNaoEncontradoException(String.format("Associado com id %s não foi encontrado", idAssociado));
    }

    public void verificarSeAssociadoJaPossuiVotoNaPauta(String idAssociado, String idPauta){
        if(votoRepository.existsByAssociadoIdAndPautaId(idAssociado, idPauta))
            throw new AssociadoPossuiVoto(String.format("Associado já possui voto contabilizado para a pauta informada"));
    }

    public void verificarSeSessaoExiste(String idSessao) {
        Optional<Sessao> sessao = sessaoRepository.findById(idSessao);
        if(sessao.isEmpty())
            throw new RecursoNaoEncontradoException(String.format("Sessão com id %s não foi encontrada", idSessao));
    }

    public void verificarSeSessaoEstaAberta(String idSessao) {
        Sessao sessao = sessaoRepository.findById(idSessao).get();
        if(sessao.getStatus().equals(Boolean.FALSE))
            throw new SessaoEncerradaException(String.format("A sessão %s já foi encerrada para votação", idSessao));
    }

    public void verificarSePautaExiste(String idPauta) {
        Optional<Pauta> pauta = pautaRepository.findById(idPauta);
        if(pauta.isEmpty())
            throw new RecursoNaoEncontradoException(String.format("Pauta com id %s não foi encontrada", idPauta));
    }

}
