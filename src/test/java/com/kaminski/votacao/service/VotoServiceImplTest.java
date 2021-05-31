package com.kaminski.votacao.service;

import com.kaminski.votacao.exception.AssociadoPossuiVoto;
import com.kaminski.votacao.exception.RecursoNaoEncontradoException;
import com.kaminski.votacao.model.enuns.OpcaoVoto;
import com.kaminski.votacao.model.documents.Pauta;
import com.kaminski.votacao.model.documents.Voto;
import com.kaminski.votacao.model.dto.ResultadoPautaDto;
import com.kaminski.votacao.model.form.VotoForm;
import com.kaminski.votacao.repository.VotoRepository;
import com.kaminski.votacao.util.Validacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(value = MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class VotoServiceImplTest {

    @InjectMocks
    private VotoServiceImpl votoService;

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private PautaServiceImpl pautaService;

    @Mock
    private Validacao validacao;

    @Test
    @DisplayName("Sucesso - Realizar voto")
    void deveRetornarUmVotoQuandoRegistradoComSucesso(){

        var votoEsperado = Voto.builder()
                .id("001")
                .opcaoVoto(OpcaoVoto.SIM)
                .associadoId("111")
                .pautaId("222")
                .sessaoId("333")
                .build();

        var votoForm = VotoForm.builder()
                .opcaoVoto(OpcaoVoto.SIM)
                .idAssociado("111")
                .idPauta("222")
                .idSessao("333")
                .build();

        when(votoRepository.save(any(Voto.class))).thenReturn(votoEsperado);
        var votoMock = votoService.votar(votoForm);

        assertThat(votoEsperado, is(votoMock));

    }

    @Test
    @DisplayName("Erro - Realizar voto com associado inexistente")
    void deveRetornarUmaExcecaoQuandoAssociadoNaoExite(){

        var votoForm = VotoForm.builder()
                .opcaoVoto(OpcaoVoto.SIM)
                .idAssociado("111")
                .idPauta("222")
                .idSessao("333")
                .build();

        doThrow(RecursoNaoEncontradoException.class).when(validacao).verificarSeAssociadoExiste(anyString());
        Assertions.assertThrows(RecursoNaoEncontradoException.class, () ->{
            votoService.votar(votoForm);
        });

    }

    @Test
    @DisplayName("Erro - Realizar voto com sessao inexistente")
    void deveRetornarUmaExcecaoQuandoSessaoNaoExite(){

        var votoForm = VotoForm.builder()
                .opcaoVoto(OpcaoVoto.SIM)
                .idAssociado("111")
                .idPauta("222")
                .idSessao("333")
                .build();

        doThrow(RecursoNaoEncontradoException.class).when(validacao).verificarSeSessaoExiste(anyString());
        Assertions.assertThrows(RecursoNaoEncontradoException.class, () ->{
            votoService.votar(votoForm);
        });

    }

    @Test
    @DisplayName("Erro - Realizar voto com pauta inexistente")
    void deveRetornarUmaExcecaoQuandoPautaNaoExite(){

        var votoForm = VotoForm.builder()
                .opcaoVoto(OpcaoVoto.SIM)
                .idAssociado("111")
                .idPauta("222")
                .idSessao("333")
                .build();

        doThrow(RecursoNaoEncontradoException.class).when(validacao).verificarSePautaExiste(anyString());
        Assertions.assertThrows(RecursoNaoEncontradoException.class, () ->{
            votoService.votar(votoForm);
        });

    }

    @Test
    @DisplayName("Erro - Realizar voto com sessao encerrada")
    void deveRetornarUmaExcecaoQuandoSessaoEstiverEncerrada(){

        var votoForm = VotoForm.builder()
                .opcaoVoto(OpcaoVoto.SIM)
                .idAssociado("111")
                .idPauta("222")
                .idSessao("333")
                .build();

        doThrow(RecursoNaoEncontradoException.class).when(validacao).verificarSeSessaoEstaAberta(anyString());
        Assertions.assertThrows(RecursoNaoEncontradoException.class, () ->{
            votoService.votar(votoForm);
        });

    }

    @Test
    @DisplayName("Erro - Realizar voto com associado ja possui voto")
    void deveRetornarUmaExcecaoQuandoAssociadoJaPossuiVotoNaPauta(){

        var votoForm = VotoForm.builder()
                .opcaoVoto(OpcaoVoto.SIM)
                .idAssociado("111")
                .idPauta("222")
                .idSessao("333")
                .build();

        doThrow(AssociadoPossuiVoto.class).when(validacao).verificarSeAssociadoJaPossuiVotoNaPauta(anyString(), anyString());
        Assertions.assertThrows(AssociadoPossuiVoto.class, () ->{
            votoService.votar(votoForm);
        });

    }

    @Test
    @DisplayName("Sucesso - Buscar resultados votos por pauta")
    void deveRetornarResultadoPautaDto(){

        var resultadoEsperado = ResultadoPautaDto.builder()
                .titulo("Teste")
                .descricao("..")
                .quantidadeVotosNao(1)
                .quantidadeVotosSim(1)
                .build();

        var pautaEsperada = Pauta.builder()
                .id("123")
                .titulo("Teste")
                .descricao("..")
                .build();

        when(pautaService.buscarPorId("123")).thenReturn(pautaEsperada);
        when(votoRepository.countByPautaIdAndOpcaoVoto(anyString(), any(OpcaoVoto.class))).thenReturn(1);

        var resultado = votoService.buscarTotalVotosPorPauta("123");
        assertThat(resultadoEsperado, is(resultado));

    }

    @Test
    @DisplayName("Erro - Buscar resultados votos com pauta inexistente")
    void deveRetornarUmaExcecaoQuandoPautaNaoExiste(){

        doThrow(RecursoNaoEncontradoException.class).when(validacao).verificarSePautaExiste(anyString());
        Assertions.assertThrows(RecursoNaoEncontradoException.class, () ->{
            votoService.buscarTotalVotosPorPauta(anyString());
        });

    }

}
