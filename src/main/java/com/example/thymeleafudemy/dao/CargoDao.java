package com.example.thymeleafudemy.dao;

import com.example.thymeleafudemy.domain.Cargo;

import java.util.List;

public interface CargoDao {

    void save(Cargo entity);

    void update(Cargo entity);

    void delete(Long id);

    Cargo findById(Long id);

    List<Cargo> findAll();

}
