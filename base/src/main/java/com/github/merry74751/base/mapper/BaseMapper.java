package com.github.merry74751.base.mapper;

import java.util.List;

public interface BaseMapper<T, Q> {
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
