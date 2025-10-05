package com.transacoes.service.transacaoRecorrente;

import com.common.service.BaseService;
import com.transacoes.domain.entities.TransacaoRecorrente;

import java.time.LocalDate;
import java.util.List;

public interface TransacaoRecorrenteService extends BaseService<TransacaoRecorrente,Long> {
    List<TransacaoRecorrente> findByProximaDataGeracaoLessThanEqual(LocalDate hoje);

    void persitNewTransactio(TransacaoRecorrente transacaoRecorrente);
}
