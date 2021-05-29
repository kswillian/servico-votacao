package com.kaminski.votacao.service;

import com.kaminski.votacao.exception.AssociadoPossuiCadastroException;
import com.kaminski.votacao.exception.RecursoNaoEncontradoException;
import com.kaminski.votacao.model.documents.Associado;
import com.kaminski.votacao.model.form.AssociadoForm;
import com.kaminski.votacao.repository.AssociadoRepository;
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
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(value = MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AssociadoServiceImplTest {

    @InjectMocks
    private AssociadoServiceImpl associadoService;

    @Mock
    private AssociadoRepository associadoRepository;

    @Mock
    private Validacao validacao;

    @Test
    @DisplayName("Sucesso - Cadastro associado")
    void deveRetornarUmAssociadoQuandoSalvoComSucesso(){

        var associadoEsperado = Associado.builder()
                .id("123")
                .cpf("123")
                .nome("Associado teste")
                .build();

        var associadoForm = AssociadoForm.builder()
                .cpf("1234")
                .nome("Associado teste")
                .build();

        when(associadoRepository.save(any(Associado.class))).thenReturn(associadoEsperado);
        var associadoMock = associadoService.cadastrar(associadoForm);
        assertThat(associadoEsperado, is(associadoMock));

    }

    @Test
    @DisplayName("Erro - Cadastro associado com cpf duplicado na base")
    void deveRetornarUmaExececaoQuandoCadastrarUmAssociadoComCpfDuplicado(){

        var associadoForm = AssociadoForm.builder()
                .cpf("1234")
                .nome("Associado teste")
                .build();

        when(associadoRepository.existsByCpf(anyString())).thenReturn(Boolean.TRUE);
        doThrow(AssociadoPossuiCadastroException.class).when(validacao).verificarSeCpfJaPossuiCadastro(anyString());

        Assertions.assertThrows(AssociadoPossuiCadastroException.class, () ->{
            associadoService.cadastrar(associadoForm);
        });

    }

    @Test
    @DisplayName("Sucesso - Listar associados")
    void deveRetornarUmaListaDeAssociados(){

        var associadoDB = Associado.builder()
                .id("123")
                .cpf("123")
                .nome("Associado teste")
                .build();

        var associados = Arrays.asList(associadoDB, associadoDB);
        when(associadoRepository.findAll()).thenReturn(associados);

        var associadosMock = associadoService.listarTodos();
        assertThat(associados, is(associadosMock));

    }

    @Test
    @DisplayName("Sucesso - Buscar associado por id")
    void deveRetornarUmAssociadoQuandoExistirAssociado(){

        var associadoEsperado = Associado.builder()
                .id("123")
                .cpf("123")
                .nome("Associado teste")
                .build();

        when(associadoRepository.findById("123")).thenReturn(java.util.Optional.ofNullable(associadoEsperado));

        var associadoMock = associadoService.buscarPorId("123");
        assertThat(associadoEsperado, is(associadoMock));

    }

    @Test
    @DisplayName("Erro - Buscar associado por id")
    void deveRetornarUmaExcecaoQuandoNaoExistirAssociado(){
        doThrow(RecursoNaoEncontradoException.class).when(validacao).verificarSeAssociadoExiste(anyString());
        Assertions.assertThrows(RecursoNaoEncontradoException.class, () ->{
            associadoService.buscarPorId("123");
        });
    }

}
