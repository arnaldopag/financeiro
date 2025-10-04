package com.controle.transacoes.service.transacaoRecorrente;

import com.controle.transacoes.domain.entitys.TransacaoRecorrente;
import com.controle.transacoes.repository.transacaoRecorrente.TransacaoRecorrenteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransacaoRecorrenteServiceImpl implements TransacaoRecorrenteService{

    private final TransacaoRecorrenteRepository transacaoRecorrenteRepository;


    @Override
    public List<TransacaoRecorrente> findByProximaDataGeracaoLessThanEqual(LocalDate hoje) {
        return transacaoRecorrenteRepository.findByProximaDataGeracaoLessThanEqual(hoje);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void persitNewTransactio(TransacaoRecorrente transacaoRecorrente) {
        transacaoRecorrenteRepository.save(transacaoRecorrente);
    }
}
