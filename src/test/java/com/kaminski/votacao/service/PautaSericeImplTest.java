package com.kaminski.votacao.service;

import com.kaminski.votacao.exception.RecursoNaoEncontradoException;
import com.kaminski.votacao.model.documents.Pauta;
import com.kaminski.votacao.model.form.PautaForm;
import com.kaminski.votacao.repository.PautaRepository;
import com.kaminski.votacao.util.Validacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(value = MockitoExtension.class)
class PautaSericeImplTest {

    @InjectMocks
    private PautaServiceImpl pautaService;

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private Validacao validacao;

    @Test
    @DisplayName("Sucesso - Cadastro pauta com sucesso")
    void deveRetornaUmaPautaQuandoSalvoComSucesso(){

        var pautaDB = Pauta.builder()
                .id("123")
                .titulo("Titulo teste")
                .descricao("Descricao teste")
                .build();

        when(pautaRepository.save(any(Pauta.class))).thenReturn(pautaDB);

        var pautaForm = PautaForm.builder()
                .titulo("Titulo teste")
                .descricao("Descricao teste")
                .build();

        var pautaMock = pautaService.cadastrar(pautaForm);
        assertThat(pautaDB, is(pautaMock));

    }

    @Test
    @DisplayName("Sucesso - Buscar todas as pautas")
    void deveRetornaListaDePautas(){

        var pautaDB = Pauta.builder()
                .id("123")
                .titulo("Titulo teste")
                .descricao("Descricao teste")
                .build();

        var pautas = Arrays.asList(pautaDB, pautaDB);
        when(pautaRepository.findAll()).thenReturn(pautas);

        var pautasMock = pautaService.listarTodos();
        assertThat(pautasMock, is(pautas));

    }

    @Test
    @DisplayName("Sucesso - Buscar pauta por id")
    void deveRetornaUmaPautaQuandoExistirPauta(){

        var pautaDB = Pauta.builder()
                .id("123")
                .titulo("Titulo teste")
                .descricao("Descricao teste")
                .build();

        when(pautaRepository.findById(anyString())).thenReturn(Optional.ofNullable(pautaDB));

        var pautaMock = pautaService.buscarPorId("123");
        assertThat(pautaDB, is(pautaMock));

    }

    @Test
    @DisplayName("Erro - Buscar pauta por id")
    void deveRetornaUmaExcecaoQuandoNaoExistirPauta(){
        doThrow(RecursoNaoEncontradoException.class).when(validacao).verificarSePautaExiste(anyString());
        Assertions.assertThrows(RecursoNaoEncontradoException.class, () ->{
            pautaService.buscarPorId("123");
        });
    }

}
