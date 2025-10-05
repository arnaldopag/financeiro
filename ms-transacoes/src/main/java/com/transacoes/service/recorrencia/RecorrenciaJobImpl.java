package com.transacoes.service.recorrencia;

import com.transacoes.domain.entities.Transacao;
import com.transacoes.domain.entities.TransacaoRecorrente;
import com.transacoes.domain.enums.TipoRecorrencia;
import com.transacoes.domain.enums.TipoTransacao;
import com.transacoes.service.transacao.TransacaoService;
import com.transacoes.service.transacaoRecorrente.TransacaoRecorrenteService;
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
                plano.getTipoRecorrencia() == TipoRecorrencia.UNICA) {
            return null;
        }
        switch (plano.getTipoRecorrencia()) {
            case MENSAL, PARCELADA:
                return dataAtual.plusMonths(1);
            case ANUAL:
                return dataAtual.plusYears(1);
            default:
                throw new IllegalStateException("Tipo de recorrencia Invalida" + plano.getTipoRecorrencia());
        }
    }


    private String gerarDescricaoParcela(TransacaoRecorrente plano) {
        String base = plano.getDescricao();
        if (plano.getTipoRecorrencia() == TipoRecorrencia.PARCELADA) {
            int proximaParcela = plano.getParcelasGeradas() + 1;
            return String.format("%s (%d/%d)", base, proximaParcela, plano.getTotalParcelas());
        }
        return base;
    }
}