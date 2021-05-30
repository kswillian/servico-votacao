package com.kaminski.votacao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaminski.votacao.exception.RecursoNaoEncontradoException;
import com.kaminski.votacao.model.documents.Associado;
import com.kaminski.votacao.model.form.AssociadoForm;
import com.kaminski.votacao.service.AssociadoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(value = MockitoExtension.class)
class AssociadoControllerTest {

    @Mock
    private AssociadoServiceImpl associadoService;

    private MockMvc mockMvc;

    @BeforeEach
    public void onInit(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(new AssociadoController(associadoService)).build();
    }

    @Test
    @DisplayName("Sucesso - Cadastrar associado")
    void quandoCadastrarUmAssociadoDeveRetornarStatus201() throws Exception {

        var associadoEsperado = Associado.builder()
                .id("123")
                .cpf("123")
                .nome("Associado teste")
                .build();

        var associadoForm = AssociadoForm.builder()
                .cpf("1234")
                .nome("Associado teste")
                .build();

        when(associadoService.cadastrar(any(AssociadoForm.class))).thenReturn(associadoEsperado);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(associadoForm);

        this.mockMvc.perform(post("/api/v1/associados").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    @DisplayName("Erro - Cadastrar associado com CPF nulo")
    void quandoCadastrarUmAssociadoSemCpfDeveRetornarStatus400() throws Exception {

        var associadoForm = AssociadoForm.builder()
                .cpf(null)
                .nome("Associado teste")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(associadoForm);

        this.mockMvc.perform(post("/api/v1/associados").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("Erro - Cadastrar associado com nome nulo")
    void quandoCadastrarUmAssociadoSemNomeDeveRetornarStatus400() throws Exception {

        var associadoForm = AssociadoForm.builder()
                .cpf("123")
                .nome(null)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(associadoForm);

        this.mockMvc.perform(post("/api/v1/associados").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("Sucesso - Buscar associados")
    void quandoSolicitarOsRegistrosDeAssociadosDeveRetornarStatus200() throws Exception {

        var associado = Associado.builder()
                .id("123")
                .cpf("123")
                .nome("Associado teste")
                .build();

        var associadosListEsperado = Arrays.asList(associado);
        when(associadoService.listarTodos()).thenReturn(associadosListEsperado);

        this.mockMvc.perform(get("/api/v1/associados")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Sucesso - Buscar associado pelo id")
    void quandoSolicitarUmAssociadoPeloIdDeveRetornarStatus200() throws Exception {

        var associadosEsperado = Associado.builder()
                .id("1")
                .cpf("123")
                .nome("Associado teste")
                .build();

        when(associadoService.buscarPorId(anyString())).thenReturn(associadosEsperado);

        this.mockMvc.perform(get("/api/v1/associados/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Associado teste")));

    }

    @Test
    @DisplayName("Erro - Buscar associado pelo id")
    void quandoSolicitarUmAssociadoPeloIdInvalidoDeveRetornarStatus404() throws Exception {

        when(associadoService.buscarPorId(anyString())).thenThrow(RecursoNaoEncontradoException.class);

        this.mockMvc.perform(get("/api/v1/associados/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

}
