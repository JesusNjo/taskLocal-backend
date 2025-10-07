package com.example.backend.dev.commons.generics;

import java.util.List;
import java.util.Optional;

public interface GenericJPA<E,K> {

    Optional<E> findById(final K id);

    E save(final E entity);

    List<E> saveAll(final List<E> entities);

    void delete(final E entity);
}
