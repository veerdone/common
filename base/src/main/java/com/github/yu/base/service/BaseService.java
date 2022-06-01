package com.github.yu.base.service;


import java.io.Serializable;
import java.util.List;

public interface BaseService<T, Q> {
    void insert(T t);
    
    void deleteById(Serializable id);

    void deleteByName(String name);

    void updateById(T t);

    T getById(Long id);

    T getByEntity(T t);

    T getByName(String name);

    List<T> list();

    List<T> listByEntity(T t);

    List<T> listByQuery(Q q);

    List<T> page();

    List<T> pageByEntity(T t);

    List<T> pageByQuery(Q query);
}
