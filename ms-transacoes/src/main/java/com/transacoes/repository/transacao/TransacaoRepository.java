package com.transacoes.repository.transacao;

import com.common.repository.jpa.BaseRepository;
import com.transacoes.domain.entities.Transacao;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends BaseRepository<Transacao, Long>, TransacaoRepositoryCustom {
}