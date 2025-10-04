package com.controle.transacoes.service.transacao;

import com.controle.transacoes.domain.entitys.Transacao;
import com.controle.transacoes.domain.enums.TipoRecorrencia;
import com.controle.transacoes.domain.enums.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface TransacaoService {

    void persistNewTransaction(Transacao transacao);

    Transacao buildTransacao(Long contaId, BigDecimal valorBase, LocalDate hoje, TipoRecorrencia tipoRecorrencia, Integer totalParcelas, String comentario, TipoTransacao tipoTransacao);
}
