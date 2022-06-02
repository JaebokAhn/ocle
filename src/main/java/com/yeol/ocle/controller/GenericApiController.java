package com.yeol.ocle.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
public abstract class GenericApiController<T, ID> {

    @Autowired
    private JpaRepository<T, ID> repository;


    @GetMapping("/{id}")
    public T select(@PathVariable ID id) {
        log.debug("GenericController.select - {}",id);
        return repository.findById(id).get();
    }

    @GetMapping
    public List<T> list(){
        log.debug("GenericController.list");
        return repository.findAll();
    }

    @GetMapping("/paging")
    public Page<T> selectPage(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        return repository.findAll(pageable);
    }

    @PostMapping
    public T create(@RequestBody T t) {
        log.debug("GenericController.create - {}",t.toString());
        T created = repository.save(t);
        return created;
    }

    @PutMapping("/{id}")
    public T update(@RequestBody T t) {
        log.debug("GenericController.update - {}",t.toString());
        T updated = repository.save(t);
        return updated;
    }

//    @DeleteMapping("/{id}")
//    public boolean delete(@PathVariable ID id) {
//        log.debug("GenericController.delete - {}",id);
//
//        repository.deleteById(id);
//        return true;
//    }
}
