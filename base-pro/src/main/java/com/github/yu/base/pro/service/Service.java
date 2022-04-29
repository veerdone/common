package com.github.yu.base.pro.service;

import java.io.Serializable;
import java.util.List;

public interface Service<T, Q extends T> {
    void insert(T t);

    void deleteById(Serializable id);

    void updateById(T t);

    T getById(Serializable id);

    T getByEntity(T t);

    T getByQuery(Q q);

    List<T> list();

    List<T> listByEntity(T t);

    List<T> listByQuery(Q q);

    List<T> page();

    List<T> pageByEntity(T t);

    List<T> pageByQuery(Q q);
}
