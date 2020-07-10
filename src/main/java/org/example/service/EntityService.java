package org.example.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EntityService<T> {
    T create(T entity);
    T update(T entity);
    void delete(Long id);
    T getById(Long id);
    List<T> getEntities();
}
