package com.common.service;

import com.common.repository.jpa.BaseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class BaseServiceImpl<T, ID> implements BaseService<T, ID> {

    protected BaseRepository<T, ID> repository;
    @Autowired
    private ApplicationContext context;

    @SuppressWarnings("unchecked")
    protected void initRepository(Object repositoryBean) {
        this.repository = (BaseRepository<T, ID>) repositoryBean;
    }

    @Override
    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public T persistNewTransaction(T entity) {
        return repository.save(entity);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteEntity(T entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        repository.deleteByUuid(uuid);
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return repository.existsByUuid(uuid);
    }

    @Override
    public Optional<T> findByUuid(UUID uuid) {
        return repository.findByUuid(uuid);
    }
}
