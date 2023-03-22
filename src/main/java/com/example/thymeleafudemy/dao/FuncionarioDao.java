package com.example.thymeleafudemy.dao;

import com.example.thymeleafudemy.domain.Funcionario;

import java.util.List;

public interface FuncionarioDao {

    void save(Funcionario entity);

    void update(Funcionario entity);

    void delete(Long id);

    Funcionario findById(Long id);

    List<Funcionario> findAll();

}
