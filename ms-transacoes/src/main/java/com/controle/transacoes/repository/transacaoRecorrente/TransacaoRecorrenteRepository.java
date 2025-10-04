package com.controle.transacoes.repository.transacaoRecorrente;

import com.controle.transacoes.domain.entitys.TransacaoRecorrente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransacaoRecorrenteRepository extends JpaRepository<TransacaoRecorrente,Long>, TransacaoRecorrenteRepositoryCustom {
    List<TransacaoRecorrente> findByProximaDataGeracaoLessThanEqual(LocalDate hoje);
}
