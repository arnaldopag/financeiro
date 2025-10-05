package com.transacoes.repository.transacaoRecorrente;

import com.common.repository.jpa.BaseRepository;
import com.transacoes.domain.entities.TransacaoRecorrente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransacaoRecorrenteRepository extends BaseRepository<TransacaoRecorrente,Long>, TransacaoRecorrenteRepositoryCustom {
    List<TransacaoRecorrente> findByProximaDataGeracaoLessThanEqual(LocalDate hoje);
}
