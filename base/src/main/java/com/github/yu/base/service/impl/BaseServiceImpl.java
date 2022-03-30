package com.github.yu.base.service.impl;

import com.github.yu.base.mapper.BaseMapper;
import com.github.yu.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class BaseServiceImpl<T, Q, M extends BaseMapper<T, Q>>
        implements BaseService<T, Q, M> {
    @Autowired
    BaseMapper<T, Q> m;

    @Override
    public void insert(T t) {
        m.insert(t);
    }

    @Override
    public void deleteById(Integer id) {
        m.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        m.deleteByName(name);
    }

    @Override
    public void updateById(T t) {
        m.updateById(t);
    }

    @Override
    public T getById(Integer id) {
        return m.getById(id);
    }

    @Override
    public T getByName(String name) {
        return m.getByName(name);
    }

    @Override
    public List<T> list() {
        return m.list();
    }

    @Override
    public List<T> pageList() {
        return m.pageList();
    }

    @Override
    public List<T> pageListByQuery(Q query) {
        return m.pageListByQuery(query);
    }
}
