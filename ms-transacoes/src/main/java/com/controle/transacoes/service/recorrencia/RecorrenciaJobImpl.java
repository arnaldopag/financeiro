package com.controle.transacoes.service.recorrencia;

import com.controle.transacoes.domain.entitys.Transacao;
import com.controle.transacoes.domain.entitys.TransacaoRecorrente;
import com.controle.transacoes.domain.enums.TipoRecorrencia;
import com.controle.transacoes.domain.enums.TipoTransacao;
import com.controle.transacoes.service.transacao.TransacaoService;
import com.controle.transacoes.service.transacaoRecorrente.TransacaoRecorrenteService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecorrenciaJobImpl implements RecorrenciaJob {

    private final TransacaoRecorrenteService transacaoRecorrenteService;
    private final TransacaoService transacaoService;

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void gerarTransacoesRecorrentes() {
        LocalDate hoje = LocalDate.now();

        List<TransacaoRecorrente> planosParaGerar = transacaoRecorrenteService
                .findByProximaDataGeracaoLessThanEqual(hoje);

        for (TransacaoRecorrente plano : planosParaGerar) {
            Transacao novaTransacao = transacaoService.buildTransacao(
                    plano.getContaId(),
                    plano.getValorBase(),
                    hoje,
                    plano.getTipoRecorrencia(),
                    plano.getTotalParcelas(),
                    gerarDescricaoParcela(plano),
                    TipoTransacao.DESPESA);
            transacaoService.persistNewTransaction(novaTransacao);

            plano.setParcelasGeradas(plano.getParcelasGeradas() + 1);
            plano.setProximaDataGeracao(calcularProximaData(plano));
            transacaoRecorrenteService.persitNewTransactio(plano);
        }
    }
    private LocalDate calcularProximaData(TransacaoRecorrente plano) {
        LocalDate dataAtual = plano.getProximaDataGeracao();

        if ((plano.getTipoRecorrencia() == TipoRecorrencia.PARCELADA &&
        plano.getParcelasGeradas() >= plano.getTotalParcelas()) ||
                plano.getTipoRecorrencia() == TipoRecorrencia.UNICA){
            return null;
        }
        switch (plano.getTipoRecorrencia()){
            case MENSAL, PARCELADA:
                return dataAtual.plusMonths(1);
            case ANUAL :
                return dataAtual.plusYears(1);
            default:
                throw new IllegalStateException("Tipo de recorrencia Invalida" + plano.getTipoRecorrencia());
        }
    }


    private String gerarDescricaoParcela(TransacaoRecorrente plano) {
        String base = plano.getDescricao();
        if(plano.getTipoRecorrencia() == TipoRecorrencia.PARCELADA){
            int proximaParcela = plano.getParcelasGeradas() + 1 ;
            return String.format("%s (%d/%d)",base,proximaParcela,plano.getTotalParcelas());
        }
        return base;
    }
}