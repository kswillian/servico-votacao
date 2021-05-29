package com.kaminski.votacao.service;

import com.kaminski.votacao.exception.RecursoNaoEncontradoException;
import com.kaminski.votacao.model.documents.Sessao;
import com.kaminski.votacao.model.dto.SessaoDto;
import com.kaminski.votacao.model.form.SessaoForm;
import com.kaminski.votacao.repository.SessaoRepository;
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

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(value = MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SessaoServiceImplTest {

    @InjectMocks
    private SessaoServiceImpl sessaoService;

    @Mock
    private SessaoRepository sessaoRepository;

    @Mock
    private Validacao validacao;

    @Test
    @DisplayName("Sucesso - Abri sessao")
    void deveRetornarUmaSessaoDtoQuandoAbertaComSucesso(){

        var sessaoEsperada = Sessao.builder()
                .id("123")
                .pautaId("1")
                .status(Boolean.TRUE)
                .divulgada(Boolean.FALSE)
                .build();

        var sessaoDtoEsperada = SessaoDto.builder()
                .id("123")
                .pauta("1")
                .status(Boolean.TRUE)
                .build();

        var sessaoForm = SessaoForm.builder()
                .pautaId("1")
                .tempoDuracao(10)
                .build();

        when(sessaoRepository.save(any(Sessao.class))).thenReturn(sessaoEsperada);
        var sessaoMock = sessaoService.abrir(sessaoForm);
        assertThat(sessaoDtoEsperada, is(sessaoMock));

    }

    @Test
    @DisplayName("Erro - Abri sessao para uma pauta invalida")
    void deveRetornarUmaExececaoQuandoAbrirUmaSessaoComPautaInvalida(){

        var sessaoForm = SessaoForm.builder()
                .pautaId("1")
                .tempoDuracao(10)
                .build();

        doThrow(RecursoNaoEncontradoException.class).when(validacao).verificarSePautaExiste(anyString());
        Assertions.assertThrows(RecursoNaoEncontradoException.class, () ->{
            sessaoService.abrir(sessaoForm);
        });

    }

    @Test
    @DisplayName("Sucesso - Encerrar sessao")
    void deveRetornarUmaSessaoDtoQuandoEncerrarComSucesso(){

        var sessaoEsperada = Sessao.builder()
                .id("123")
                .pautaId("1")
                .status(Boolean.FALSE)
                .divulgada(Boolean.FALSE)
                .build();

        var sessao = Sessao.builder()
                .id("123")
                .pautaId("1")
                .status(Boolean.TRUE)
                .divulgada(Boolean.FALSE)
                .build();

        when(sessaoRepository.save(any(Sessao.class))).thenReturn(sessao);
        var sessaoMock = sessaoService.encerrar(sessao);

        assertThat(sessaoEsperada, is(sessaoMock));

    }

    @Test
    @DisplayName("Sucesso - Divulgar sessao")
    void deveRetornarUmaSessaoDtoQuandoDivulgarComSucesso(){

        var sessaoEsperada = Sessao.builder()
                .id("123")
                .pautaId("1")
                .status(Boolean.FALSE)
                .divulgada(Boolean.TRUE)
                .build();

        var sessao = Sessao.builder()
                .id("123")
                .pautaId("1")
                .status(Boolean.FALSE)
                .divulgada(Boolean.FALSE)
                .build();

        when(sessaoRepository.save(any(Sessao.class))).thenReturn(sessao);
        var sessaoMock = sessaoService.divulgar(sessao);

        assertThat(sessaoEsperada, is(sessaoMock));

    }

    @Test
    @DisplayName("Sucesso - Listar sessoes")
    void deveRetornarUmaListaDeSessoes(){

        var sessao = Sessao.builder()
                .id("123")
                .pautaId("1")
                .status(Boolean.TRUE)
                .divulgada(Boolean.FALSE)
                .build();

        var sessoes = Arrays.asList(sessao, sessao);
        when(sessaoRepository.findAll()).thenReturn(sessoes);

        var sessoesMock = sessaoService.listarTodos();
        assertThat(sessoes, is(sessoesMock));

    }

    @Test
    @DisplayName("Sucesso - Listar sessoes abertas")
    void deveRetornarUmaListaDeSessoesAbertas(){

        var sessao = Sessao.builder()
                .id("123")
                .pautaId("1")
                .dataHoraInicio(LocalDateTime.now())
                .dataHoraFim(LocalDateTime.now().minusMinutes(10))
                .status(Boolean.TRUE)
                .divulgada(Boolean.FALSE)
                .build();

        var sessoes = Arrays.asList(sessao, sessao);
        when(sessaoRepository.findByStatusTrue()).thenReturn(sessoes);

        var sessoesMock = sessaoService.listarSessoesAbertas();
        assertThat(sessoes, is(sessoesMock));

    }

    @Test
    @DisplayName("Sucesso - Listar sessoes não divulgadas")
    void deveRetornarUmaListaDeSessoesNaoDivulgadas(){

        var sessao = Sessao.builder()
                .id("123")
                .pautaId("1")
                .dataHoraInicio(LocalDateTime.now())
                .dataHoraFim(LocalDateTime.now().minusMinutes(10))
                .status(Boolean.FALSE)
                .divulgada(Boolean.FALSE)
                .build();

        var sessoes = Arrays.asList(sessao, sessao);
        when(sessaoRepository.findByStatusFalseAndDivulgadaFalse()).thenReturn(sessoes);

        var sessoesMock = sessaoService.listarSessoesEncerradasNaoDivulgadas();
        assertThat(sessoes, is(sessoesMock));

    }

    @Test
    @DisplayName("Sucesso - Buscar sessao por id")
    void deveRetornarUmaSessaoQuandoExistirSesso(){

        var sessaoEsperada = Sessao.builder()
                .id("123")
                .pautaId("1")
                .dataHoraInicio(LocalDateTime.now())
                .dataHoraFim(LocalDateTime.now().plusMinutes(10))
                .status(Boolean.TRUE)
                .divulgada(Boolean.FALSE)
                .build();

        when(sessaoRepository.findById("123")).thenReturn(java.util.Optional.ofNullable(sessaoEsperada));

        var associadoMock = sessaoService.buscarPorId("123");
        assertThat(sessaoEsperada, is(associadoMock));

    }

    @Test
    @DisplayName("Erro - Buscar sessao por id")
    void deveRetornarUmaExcecaoQuandoNaoExistirSesso(){
        doThrow(RecursoNaoEncontradoException.class).when(validacao).verificarSeAssociadoExiste(anyString());
        Assertions.assertThrows(RecursoNaoEncontradoException.class, () ->{
            sessaoService.buscarPorId("123");
        });
    }

}
