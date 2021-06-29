package com.aram.mapper;

public interface Mapper<T, V> {
    V mapToDTO(T t);
}
