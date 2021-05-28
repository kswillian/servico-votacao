package com.kaminski.votacao.service;

import com.kaminski.votacao.model.documents.OpcaoVoto;
import com.kaminski.votacao.model.documents.Voto;
import com.kaminski.votacao.model.dto.ResultadoPautaDto;
import com.kaminski.votacao.model.form.VotoForm;
import com.kaminski.votacao.repository.VotoRepository;
import com.kaminski.votacao.util.Validacao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VotoServiceImpl implements VotoService{

    private VotoRepository votoRepository;
    private PautaService pautaService;
    private Validacao validacao;

    @Override
    public Voto votar(VotoForm votoForm) {

        validacao.verificarSeAssociadoExiste(votoForm.getIdAssociado());
        validacao.verificarSeSessaoExiste(votoForm.getIdSessao());
        validacao.verificarSePautaExiste(votoForm.getIdPauta());
        validacao.verificarSeSessaoEstaAberta(votoForm.getIdSessao());
        validacao.verificarSeAssociadoJaPossuiVotoNaPauta(votoForm.getIdAssociado(), votoForm.getIdPauta());

        var voto = Voto.builder()
                .sessaoId(votoForm.getIdSessao())
                .opcaoVoto(votoForm.getOpcaoVoto())
                .pautaId(votoForm.getIdPauta())
                .associadoId(votoForm.getIdAssociado())
                .build();

        return votoRepository.save(voto);

    }

    @Override
    public ResultadoPautaDto buscarTotalVotosPorPauta(String idPauta) {

        validacao.verificarSePautaExiste(idPauta);
        var pauta = pautaService.buscarPorId(idPauta);

        var votos = votoRepository.findAllByPautaId(idPauta);
        var votosSim = votoRepository.countByPautaIdAndOpcaoVoto(idPauta, OpcaoVoto.SIM);
        var votosNao = votoRepository.countByPautaIdAndOpcaoVoto(idPauta, OpcaoVoto.NAO);


        return ResultadoPautaDto.builder()
                .titulo(pauta.getTitulo())
                .descricao(pauta.getDescricao())
                .quantidadeVotosNao(votosNao)
                .quantidadeVotosSim(votosSim)
                .build();

    }
}
