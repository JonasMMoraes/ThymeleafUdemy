package com.example.thymeleafudemy.web.converor;

import com.example.thymeleafudemy.domain.Departamento;
import com.example.thymeleafudemy.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDepartamentoConverter implements Converter<String, Departamento> {

    @Autowired
    private DepartamentoService service;

    @Override
    public Departamento convert(String text) {
        if (text.isEmpty()) {
            return null;
        }

        Long id = Long.parseLong(text);
        return service.buscarPorId(id);
    }
}
