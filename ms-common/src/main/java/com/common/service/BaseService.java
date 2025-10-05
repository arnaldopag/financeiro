package com.common.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BaseService<T, ID> {

    T save(T entity);

    Optional<T> findById(ID id);

    T persistNewTransaction(T entity);

    List<T> findAll();

    void deleteById(ID id);

    void deleteEntity(T entity);
    Optional<T> findByUuid(UUID uuid);
    boolean existsByUuid(UUID uuid);
    void deleteByUuid(UUID uuid);

}
