package com.example.thymeleafudemy.web.controller;

import com.example.thymeleafudemy.domain.Departamento;
import com.example.thymeleafudemy.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/departamentos")
public class DepartamentoController {

    @Autowired
    DepartamentoService departamentoService;

    @GetMapping("/cadastrar")
    public String cadastrar(Departamento departamento) {
        return "/departamento/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(Departamento departamento, RedirectAttributes attr) {
        departamentoService.salvar(departamento);
        attr.addFlashAttribute("success", "Departamento inserido com sucesso.");
        return "redirect:/departamentos/cadastrar";
    }

    @GetMapping("/listar")
    public String listar(ModelMap modelMap) {
        List<Departamento> departamentos = departamentoService.buscarTodos();
        modelMap.addAttribute("departamentos", departamentos);
        return "/departamento/lista";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap modelMap) {
        modelMap.addAttribute("departamento", departamentoService.buscarPorId(id));
        return "/departamento/cadastro";
    }

    @PostMapping("/editar")
    public String editar(Departamento departamento, RedirectAttributes attr) {
        departamentoService.editar(departamento);
        attr.addFlashAttribute("success", "Departamento editado com sucesso!");
        return "redirect:/departamentos/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) {

        if (departamentoService.departamentoTemCargos(id)) {
            model.addAttribute("fail", "Departamento não removido. Possui cargo(s) vinculado(s).");
        } else {
            departamentoService.excluir(id);
            model.addAttribute("success", "Departamento excluído com sucesso.");
        }

        return listar(model);
    }
}