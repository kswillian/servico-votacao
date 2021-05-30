package com.kaminski.votacao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaminski.votacao.model.documents.Sessao;
import com.kaminski.votacao.model.dto.SessaoDto;
import com.kaminski.votacao.model.form.SessaoForm;
import com.kaminski.votacao.service.SessaoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(value = MockitoExtension.class)
class SessaoControllerITest {

    @Mock
    private SessaoServiceImpl sessaoService;

    private MockMvc mockMvc;

    @BeforeEach
    public void onInit(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(new SessaoController(sessaoService)).build();
    }

    @Test
    @DisplayName("Sucesso - Abrir sessao")
    void quandoAbrirUmaSessaoDeveRetornarStatus201() throws Exception {

        var sessaoEsperada = SessaoDto.builder()
                .id("1")
                .pauta("1")
                .dataHoraInicio(LocalDateTime.now())
                .dataHoraFim(LocalDateTime.now().plusMinutes(10))
                .status(Boolean.TRUE)
                .build();

        var sessaoForm = SessaoForm.builder()
                .pautaId("1")
                .tempoDuracao(10)
                .build();

        when(sessaoService.abrir(any(SessaoForm.class))).thenReturn(sessaoEsperada);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(sessaoForm);

        this.mockMvc.perform(post("/api/v1/sessoes/abrir").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    @DisplayName("Erro - Abrir sessao com uma pauta nula")
    void quandoAbrirUmaSessaoComUmaPautaNulaDeveRetornarStatus400() throws Exception {

        var sessaoForm = SessaoForm.builder()
                .pautaId(null)
                .tempoDuracao(10)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(sessaoForm);

        this.mockMvc.perform(post("/api/v1/sessoes/abrir").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("Sucesso - Buscar registros de sessoe")
    void quandoSolicitarOsRegistrosDeSessoesDeveRetornarStatus200() throws Exception {

        var sessaoEsperada = Sessao.builder()
                .id("1")
                .dataHoraInicio(LocalDateTime.now())
                .dataHoraFim(LocalDateTime.now().plusMinutes(10))
                .pautaId("1")
                .status(Boolean.TRUE)
                .divulgada(Boolean.FALSE)
                .build();

        var sessaoListEsperada = Arrays.asList(sessaoEsperada);
        when(sessaoService.listarTodos()).thenReturn(sessaoListEsperada);

        this.mockMvc.perform(get("/api/v1/sessoes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
