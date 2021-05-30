package com.kaminski.votacao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaminski.votacao.model.documents.OpcaoVoto;
import com.kaminski.votacao.model.documents.Voto;
import com.kaminski.votacao.model.form.VotoForm;
import com.kaminski.votacao.service.VotoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(value = MockitoExtension.class)
public class VotoControllerTest {

    @Mock
    private VotoServiceImpl votoService;

    private MockMvc mockMvc;

    @BeforeEach
    public void onInit(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(new VotoControler(votoService)).build();
    }

    @Test
    @DisplayName("Sucesso - Registrar o voto")
    void quantoRegistrarVotoDeveRestornarStatus201() throws Exception {

        var votoEsperado = Voto.builder()
                .id("1")
                .opcaoVoto(OpcaoVoto.SIM)
                .associadoId("1")
                .pautaId("1")
                .sessaoId("1")
                .build();

        var votoForm = VotoForm.builder()
                .opcaoVoto(OpcaoVoto.SIM)
                .idAssociado("1")
                .idPauta("1")
                .idSessao("1")
                .build();


        when(votoService.votar(any(VotoForm.class))).thenReturn(votoEsperado);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(votoForm);

        this.mockMvc.perform(post("/api/v1/votos").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    @DisplayName("Erro - Registrar um voto com um associado nulo")
    void quantoRegistrarVotoComAssociadoNuloDeveRestornarStatus400() throws Exception {

        var votoForm = VotoForm.builder()
                .opcaoVoto(OpcaoVoto.SIM)
                .idAssociado(null)
                .idPauta("1")
                .idSessao("1")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(votoForm);

        this.mockMvc.perform(post("/api/v1/votos").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("Erro - Registrar um voto com uma pauta nula")
    void quantoRegistrarVotoComPautaNulaDeveRestornarStatus400() throws Exception {

        var votoForm = VotoForm.builder()
                .opcaoVoto(OpcaoVoto.SIM)
                .idAssociado("1")
                .idPauta(null)
                .idSessao("1")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(votoForm);

        this.mockMvc.perform(post("/api/v1/votos").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("Erro - Registrar um voto com uma sessao nula")
    void quantoRegistrarVotoComSessaoNulaDeveRestornarStatus400() throws Exception {

        var votoForm = VotoForm.builder()
                .opcaoVoto(OpcaoVoto.SIM)
                .idAssociado("1")
                .idPauta("1")
                .idSessao(null)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(votoForm);

        this.mockMvc.perform(post("/api/v1/votos").content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

}
