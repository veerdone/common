package com.github.yu.base.pro.controller;

import com.github.yu.base.pro.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class Controller<T, Q extends T, S extends Service<T, Q>> {
    @Autowired
    protected S service;

    @PostMapping("/insert")
    public void insert(@RequestBody T t) {
        this.service.insert(t);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable String id) {
        this.service.deleteById(id);
    }

    @PutMapping("/updateById")
    public void updateById(@RequestBody T t) {
        this.service.updateById(t);
    }

    @GetMapping("/getById/{id}")
    public T getById(@PathVariable String id) {
        return this.service.getById(id);
    }

    @PostMapping("/getByEntity")
    public T getByEntity(@RequestBody T t) {
        return this.service.getByEntity(t);
    }

    @PostMapping("/getByQuery")
    public T getByQuery(@RequestBody Q q) {
        return this.service.getByQuery(q);
    }

    @GetMapping("/list")
    public List<T> list() {
        return this.service.list();
    }

    @PostMapping("/listByEntity")
    public List<T> listByEntity(@RequestBody T t) {
        return this.service.listByEntity(t);
    }

    @PostMapping("/lstByQuery")
    public List<T> listByQuery(@RequestBody Q q) {
        return this.service.listByQuery(q);
    }

    @GetMapping("/page")
    public List<T> page() {
        return this.service.page();
    }

    @PostMapping("/pageByEntity")
    public List<T> pageByEntity(@RequestBody T t) {
        return this.service.pageByEntity(t);
    }

    @PostMapping("/pageByQuery")
    public List<T> pageByQuery(@RequestBody Q q) {
        return this.service.pageByQuery(q);
    }
}
