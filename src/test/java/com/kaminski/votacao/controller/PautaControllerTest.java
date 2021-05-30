package com.kaminski.votacao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaminski.votacao.exception.RecursoNaoEncontradoException;
import com.kaminski.votacao.model.documents.Pauta;
import com.kaminski.votacao.model.form.PautaForm;
import com.kaminski.votacao.service.PautaServiceImpl;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(value = MockitoExtension.class)
public class PautaControllerTest {

    @Mock
    private PautaServiceImpl pautaService;

    private MockMvc mockMvc;

    @BeforeEach
    public void onInit(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(new PautaController(pautaService)).build();
    }

    @Test
    @DisplayName("Sucesso - Cadastrar pauta")
    void quandoCadastrarUmaPautaDeveRetornarStatus201() throws Exception {

        var pautaEsperada = Pauta.builder()
                .id("123")
                .titulo("Pauta teste")
                .descricao(".")
                .build();

        var pautaForm = PautaForm.builder()
                .titulo("Pauta teste")
                .descricao(".")
                .build();

        when(pautaService.cadastrar(any(PautaForm.class))).thenReturn(pautaEsperada);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(pautaForm);

        this.mockMvc.perform(post("/api/v1/pautas").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    @DisplayName("Erro - Cadastrar pauta com titulo nulo")
    void quandoCadastrarUmaPautaSemTituloDeveRetornarStatus400() throws Exception {

        var pautaForm = PautaForm.builder()
                .titulo(null)
                .descricao(".")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(pautaForm);

        this.mockMvc.perform(post("/api/v1/pautas").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("Erro - Cadastrar pauta com descricao nulo")
    void quandoCadastrarUmaPautaSemDescricaoDeveRetornarStatus400() throws Exception {

        var pautaForm = PautaForm.builder()
                .titulo("Pauta teste")
                .descricao(null)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(pautaForm);

        this.mockMvc.perform(post("/api/v1/pautas").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("Sucesso - Buscar pautas")
    void quandoSolicitarOsRegistrosDePautasDeveRetornarStatus200() throws Exception {

        var pautaEsperada = Pauta.builder()
                .id("123")
                .titulo("Pauta teste")
                .descricao(".")
                .build();

        var pautasListEsperda = Arrays.asList(pautaEsperada);
        when(pautaService.listarTodos()).thenReturn(pautasListEsperda);

        this.mockMvc.perform(get("/api/v1/pautas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Sucesso - Buscar associado pelo id")
    void quandoSolicitarUmaPautaPeloIdDeveRetornarStatus200() throws Exception {

        var pautaEsperada = Pauta.builder()
                .id("123")
                .titulo("Pauta teste")
                .descricao(".")
                .build();

        when(pautaService.buscarPorId(anyString())).thenReturn(pautaEsperada);

        this.mockMvc.perform(get("/api/v1/pautas/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo", is("Pauta teste")));

    }

    @Test
    @DisplayName("Erro - Buscar associado pelo id")
    void quandoSolicitarUmaPautaPeloIdInvalidoDeveRetornarStatus404() throws Exception {

        when(pautaService.buscarPorId(anyString())).thenThrow(RecursoNaoEncontradoException.class);

        this.mockMvc.perform(get("/api/v1/pautas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
}
