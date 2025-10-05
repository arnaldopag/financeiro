package com.transacoes.service.transacao;

import com.common.service.BaseService;
import com.transacoes.domain.entities.Transacao;
import com.transacoes.domain.enums.TipoRecorrencia;
import com.transacoes.domain.enums.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface TransacaoService extends BaseService<Transacao,Long> {

    Transacao buildTransacao(Long contaId, BigDecimal valorBase, LocalDate hoje, TipoRecorrencia tipoRecorrencia, Integer totalParcelas, String comentario, TipoTransacao tipoTransacao);
}
