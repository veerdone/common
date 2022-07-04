package com.github.yu.base.pro.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yu.base.pro.mapper.Mapper;
import com.github.yu.base.pro.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public abstract class ServiceImpl<T, Q extends T, M extends Mapper<T>> implements Service<T, Q> {
    @Autowired
    protected M mapper;

    @Override
    public void insert(T t) {
        this.mapper.insert(t);
    }

    @Override
    public void deleteById(Long id) {
        this.mapper.deleteById(id);
    }

    @Override
    public void updateById(T t) {
        this.mapper.updateById(t);
    }

    @Override
    public T getById(Long id) {
        return this.mapper.selectById(id);
    }

    @Override
    public T getByEntity(T t) {
        QueryWrapper<T> queryWrapper = wrapperByEntity(t);
        return this.mapper.selectOne(queryWrapper);
    }

    @Override
    public T getByQuery(Q q) {
        QueryWrapper<T> queryWrapper = wrapperByQuery(q);
        return this.mapper.selectOne(queryWrapper);
    }

    @Override
    public List<T> list() {
        return this.mapper.selectList(null);
    }

    @Override
    public List<T> listByEntity(T t) {
        QueryWrapper<T> queryWrapper = wrapperByEntity(t);
        return this.mapper.selectList(queryWrapper);
    }

    @Override
    public List<T> listByQuery(Q q) {
        QueryWrapper<T> queryWrapper = wrapperByQuery(q);
        return this.mapper.selectList(queryWrapper);
    }

    @Override
    public List<T> page() {
        return this.list();
    }

    @Override
    public List<T> pageByEntity(T t) {
        return this.listByEntity(t);
    }

    @Override
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
                String fieldName = field.getName();
                TableField tableField = field.getAnnotation(TableField.class);
                if ((tableField != null && !tableField.exist()) || "serialVersionUID".equals(fieldName)) {
                    continue;
                }
                value = field.get(query);
                if (null != value) {
                    String column;
                    if (fieldName.endsWith("GT")) {
                        column = getColumn(tableField, fieldName, 2);
                        queryWrapper.gt(column, value);
                    } else if (fieldName.endsWith("LT")) {
                       column = getColumn(tableField, fieldName, 2);
                        queryWrapper.lt(column, value);
                    } else if (fieldName.endsWith("GE")) {
                        column = getColumn(tableField, fieldName, 2);
                        queryWrapper.ge(column, value);
                    } else if (fieldName.endsWith("LE")) {
                        column = getColumn(tableField, fieldName, 2);
                        queryWrapper.le(column, value);
                    } else if (fieldName.endsWith("LikeLEFT")) {
                        column = getColumn(tableField, fieldName, 8);
                        queryWrapper.likeLeft(column, value);
                    } else if (fieldName.endsWith("LIKE")) {
                        column = getColumn(tableField, fieldName, 4);
                        queryWrapper.like(column, value);
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        Class<?> c2 = c.getSuperclass();
        classToQueryWrapper(queryWrapper, c2, query);
    }

    private String getColumn(TableField tableField, String filedName, int length) {
        if (tableField != null && !"".equals(tableField.value())) {
            return tableField.value();
        }
        return StrUtil.toUnderlineCase(filedName.substring(0, filedName.length() - length));
    }

    protected void classToQueryWrapper(QueryWrapper<T> queryWrapper, Class c, T entity) {
        Field[] fields = c.getDeclaredFields();
        Stream<Field> fieldStream = Arrays.stream(fields);
        fieldStream.forEach(field -> {
            Object value = null;
            field.setAccessible(true);
            try {
                String fieldName = field.getName();
                TableField tableField = field.getAnnotation(TableField.class);
                if ((tableField != null && !tableField.exist()) || "serialVersionUID".equals(fieldName)) {
                    return;
                }
                value = field.get(entity);
                if (null != value) {
                    String column = StrUtil.toUnderlineCase(fieldName);
                    if (tableField != null && !"".equals(tableField.value())) {
                        column = tableField.value();
                    }
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
