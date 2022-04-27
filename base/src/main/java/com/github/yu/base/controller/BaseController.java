package com.github.yu.base.controller;

import com.github.yu.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public abstract class BaseController<T, Q, S extends BaseService<T, Q>> {
    @Autowired
    protected S service;

    @PostMapping("/insert")
    public void insert(@RequestBody T t) {
        this.service.insert(t);
    }

    @DeleteMapping("/deleteById")
    public void deleteById(@RequestBody T t) {
        this.service.deleteById(t);
    }

    @PutMapping("/updateById")
    public void updateById(@RequestBody T t) {
        this.service.updateById(t);
    }

    @GetMapping("/getById/{id}")
    public T getById(@PathVariable Long id) {
        return this.service.getById(id);
    }

    @GetMapping("/getByName")
    public T getByName(@RequestParam String name) {
        return this.service.getByName(name);
    }

    @PostMapping("/getByEntity")
    public T getByEntity(@RequestBody T t) {
        return this.service.getByEntity(t);
    }

    @GetMapping("/list")
    public List<T> list() {
        return this.service.list();
    }

    @PostMapping("/listByEntity")
    public List<T> listByEntity(@RequestBody T t) {
        return this.service.listByEntity(t);
    }

    @PostMapping("/listByQuery")
    public List<T> listByQuery(@RequestBody Q q) {
        return this.service.listByQuery(q);
    }

    @GetMapping("/page")
    public List<T> page() {
        return this.service.page();
    }

    @PostMapping("/pageByQuery")
    public List<T> pageByQuery(@RequestBody Q q) {
        return this.service.pageByQuery(q);
    }
}
