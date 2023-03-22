package com.example.thymeleafudemy.dao;

import com.example.thymeleafudemy.domain.Departamento;

import java.util.List;

public interface DepartamentoDao {

    void save(Departamento entity);

    void update(Departamento entity);

    void delete(Long id);

    Departamento findById(Long id);

    List<Departamento> findAll();

}
