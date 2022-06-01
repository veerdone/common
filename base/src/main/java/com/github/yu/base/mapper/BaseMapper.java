package com.github.yu.base.mapper;

import java.io.Serializable;
import java.util.List;

/**
 * @author yu
 */
public interface BaseMapper<T, Q> {
    /**
     * 插入方法
     * @param t 插入实体
     */
    void insert(T t);

    /**
     * 根据id删除
     * @param id 主键id
     */
    void deleteById(Serializable id);

    /**
     * 根据名称删除
     * @param name 名称
     */
    void deleteByName(String name);

    /**
     * 根据id更新
     * @param t 要被更新的实体
     */
    void updateById(T t);

    /**
     * 根据id获取
     * @param id 主键id
     * @return 实体
     */
    T getById(Long id);

    /**
     * 根据条件实体获取实体
     * @param t 条件实体
     * @return 实体
     */
    T getByEntity(T t);

    /**
     * 根据名称获取实体
     * @param name 名称
     * @return 实体
     */
    T getByName(String name);

    /**
     * 获取所有实体
     * @return 实体列表
     */
    List<T> list();

    /**
     * 根据实体条件获取实体列表
     * @param t 实体条件
     * @return 实体列表
     */
    List<T> listByEntity(T t);

    /**
     * 根据查润实体获取实体列表
     * @param q 查润实体
     * @return 实体列表
     */
    List<T> listByQuery(Q q);
}
