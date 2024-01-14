package org.example.repository;

import java.util.UUID;

public interface Repository<T> {
    void create(T t) throws RuntimeException;
    T getById(UUID uuid);
    void update(T t);
    void deleteById(UUID uuid);

}
