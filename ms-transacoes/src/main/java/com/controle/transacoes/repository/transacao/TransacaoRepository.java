package com.controle.transacoes.repository.transacao;

import com.controle.transacoes.domain.entitys.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long>,TransacaoRepositoryCustom {
}