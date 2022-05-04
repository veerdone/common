package com.github.yu.base.mapper;

import java.util.List;

public interface BaseMapper<T, Q> {
    void insert(T t);

    void deleteById(T t);

    void deleteByName(String name);

    void updateById(T t);

    T getById(Long id);

    T getByEntity(T t);

    T getByName(String name);

    List<T> list();

    List<T> listByEntity(T t);

    List<T> listByQuery(Q q);
}
