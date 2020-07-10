package org.example.mapper;

import java.util.List;

public interface CustomMapper<T,V> {
    T toDto(V entity);
    V fromDto(T dto);
    List<T> listToDto(List<V> entities);
    List<V> listFromDto(List<T> dtos);
}