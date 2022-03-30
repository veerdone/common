package com.github.yu.base.service;

import com.github.yu.base.mapper.BaseMapper;

import java.util.List;

public interface BaseService<T, Q, M extends BaseMapper<T, Q>> {
    void insert(T t);
    
    void deleteById(Integer id);

    void deleteByName(String name);

    void updateById(T t);

    T getById(Integer id);

    T getByName(String name);

    List<T> list();

    List<T> pageList();

    List<T> pageListByQuery(Q query);
}
