package com.controle.transacoes.service.transacao;

import com.controle.transacoes.domain.entitys.Transacao;
import com.controle.transacoes.domain.enums.TipoRecorrencia;
import com.controle.transacoes.domain.enums.TipoTransacao;
import com.controle.transacoes.repository.transacao.TransacaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TransacaoServiceImpl implements TransacaoService{

    private final TransacaoRepository transacaoRepository;


    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void persistNewTransaction(Transacao transacao) {
        transacaoRepository.save(transacao);
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
                .build();    }
}
