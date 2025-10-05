package com.transacoes.service.transacao;


import com.common.service.BaseServiceImpl;
import com.transacoes.domain.entities.Transacao;
import com.transacoes.domain.enums.TipoRecorrencia;
import com.transacoes.domain.enums.TipoTransacao;
import com.transacoes.repository.transacao.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransacaoServiceImpl extends BaseServiceImpl<Transacao, Long> implements TransacaoService {

    @Autowired
    public TransacaoServiceImpl(TransacaoRepository repository) {
        initRepository(TransacaoRepository.class);
    }


    @Override
    public Transacao buildTransacao(Long contaId, BigDecimal valorBase, LocalDate hoje, TipoRecorrencia tipoRecorrencia, Integer totalParcelas, String comentario, TipoTransacao tipoTransacao) {
        return Transacao.builder()
                .contaId(contaId)
                .valor(valorBase)
                .dataTransacao(hoje)
                .tipoTransacao(tipoTransacao)
                .tipoRecorrencia(tipoRecorrencia)
                .numeroParcelas(totalParcelas)
                .descricao(comentario)
                .build();
    }
}
