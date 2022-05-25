package com.github.yu.base.service.impl;

import com.github.yu.base.mapper.BaseMapper;
import com.github.yu.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class BaseServiceImpl<T, Q, M extends BaseMapper<T, Q>>
        implements BaseService<T, Q> {
    @Autowired
    protected M m;

    @Override
    public void insert(T t) {
        this.m.insert(t);
    }

    @Override
    public void deleteById(T t) {
        this.m.deleteById(t);
    }

    @Override
    public void deleteByName(String name) {
        this.m.deleteByName(name);
    }

    @Override
    public void updateById(T t) {
        this.m.updateById(t);
    }

    @Override
    public T getById(Long id) {
        return this.m.getById(id);
    }

    @Override
    public T getByEntity(T t) {
        return this.m.getByEntity(t);
    }

    @Override
    public T getByName(String name) {
        return this.m.getByName(name);
    }

    @Override
    public List<T> list() {
        return this.m.list();
    }

    @Override
    public List<T> listByEntity(T t) {
        return this.m.listByEntity(t);
    }

    @Override
    public List<T> listByQuery(Q q) {
        return this.m.listByQuery(q);
    }

    @Override
    public List<T> page() {
        return this.m.list();
    }

    @Override
    public List<T> pageByEntity(T t) {
        return this.m.listByEntity(t);
    }

    @Override
    public List<T> pageByQuery(Q query) {
        return this.m.listByQuery(query);
    }
}
