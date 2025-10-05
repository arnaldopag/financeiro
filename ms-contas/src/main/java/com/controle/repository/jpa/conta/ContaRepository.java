package com.controle.repository.jpa.conta;

import com.common.repository.jpa.BaseRepository;
import com.controle.domain.entities.Conta;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends BaseRepository<Conta,Long>,ContaRepositoryCustom {
}
