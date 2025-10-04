package com.controle.transacoes.service.transacaoRecorrente;

import com.controle.transacoes.domain.entitys.TransacaoRecorrente;

import java.time.LocalDate;
import java.util.List;

public interface TransacaoRecorrenteService {
    List<TransacaoRecorrente> findByProximaDataGeracaoLessThanEqual(LocalDate hoje);

    void persitNewTransactio(TransacaoRecorrente transacaoRecorrente);
}
