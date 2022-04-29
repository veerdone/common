package com.github.yu.base.pro.service;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yu.base.pro.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public abstract class ServiceImpl<T, Q extends T, M extends Mapper<T>> implements Service<T, Q> {
    @Autowired
    protected M mapper;

    public void insert(T t) {
        this.mapper.insert(t);
    }

    public void deleteById(Serializable id) {
        this.mapper.deleteById(id);
    }

    public void updateById(T t) {
        this.mapper.updateById(t);
    }

    public T getById(Serializable id) {
        return this.mapper.selectById(id);
    }

    public T getByEntity(T t) {
        QueryWrapper<T> queryWrapper = wrapperByEntity(t);
        return this.mapper.selectOne(queryWrapper);
    }

    public T getByQuery(Q q) {
        QueryWrapper<T> queryWrapper = wrapperByQuery(q);
        return this.mapper.selectOne(queryWrapper);
    }

    public List<T> list() {
        return this.mapper.selectList(null);
    }

    public List<T> listByEntity(T t) {
        QueryWrapper<T> queryWrapper = wrapperByEntity(t);
        return this.mapper.selectList(queryWrapper);
    }

    public List<T> listByQuery(Q q) {
        QueryWrapper<T> queryWrapper = wrapperByQuery(q);
        return this.mapper.selectList(queryWrapper);
    }

    public List<T> page() {
        return this.list();
    }

    public List<T> pageByEntity(T t) {
        return this.listByEntity(t);
    }

    public List<T> pageByQuery(Q q) {
        return this.listByQuery(q);
    }


    protected void entityToQueryWrapper(QueryWrapper<T> queryWrapper, T entity) {
        Class<?> c = entity.getClass();
        classToQueryWrapper(queryWrapper, c, entity);
    }

    protected void queryToQueryWrapper(QueryWrapper<T> queryWrapper, Q query) {
        Class<?> c = query.getClass();
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            Object value = null;
            field.setAccessible(true);
            try {
                value = field.get(query);
                if (null != value) {
                    String fieldName = field.getName();
                    TableField tableField = field.getAnnotation(TableField.class);
                    String column = null == tableField ?
                            fieldName.substring(0, fieldName.length() - 2) : tableField.value();
                    if (fieldName.endsWith("Gt")) {
                        queryWrapper.gt(column, value);
                    } else if (fieldName.endsWith("Lt")) {
                        queryWrapper.lt(column, value);
                    } else if (fieldName.endsWith("Ge")) {
                        queryWrapper.ge(column, value);
                    } else if (fieldName.endsWith("Le")) {
                        queryWrapper.le(column, value);
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        Class<?> c2 = c.getSuperclass();
        classToQueryWrapper(queryWrapper, c2, query);
    }

    protected void classToQueryWrapper(QueryWrapper<T> queryWrapper, Class c, T entity) {
        Field[] fields = c.getDeclaredFields();
        Stream<Field> fieldStream = Arrays.stream(fields);
        fieldStream.forEach(field -> {
            Object value = null;
            field.setAccessible(true);
            try {
                value = field.get(entity);
                if (null != value) {
                    String fieldName = field.getName();
                    TableField tableField = field.getAnnotation(TableField.class);
                    String column = null == tableField ? fieldName : tableField.value();
                    queryWrapper.eq(column, value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }

    protected QueryWrapper<T> wrapperByEntity(T t) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        entityToQueryWrapper(queryWrapper, t);
        return queryWrapper;
    }

    protected QueryWrapper<T> wrapperByQuery(Q q) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryToQueryWrapper(queryWrapper, q);
        return queryWrapper;
    }
}
