package com.transacoes.service.transacaoRecorrente;

import com.common.service.BaseServiceImpl;
import com.transacoes.domain.entities.TransacaoRecorrente;
import com.transacoes.repository.transacaoRecorrente.TransacaoRecorrenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransacaoRecorrenteServiceImpl extends BaseServiceImpl<TransacaoRecorrente, Long> implements TransacaoRecorrenteService {

    private final TransacaoRecorrenteRepository transacaoRecorrenteRepository;

    @Autowired
    public TransacaoRecorrenteServiceImpl(TransacaoRecorrenteRepository repository) {
        this.transacaoRecorrenteRepository = repository;
        initRepository(repository);
    }

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
