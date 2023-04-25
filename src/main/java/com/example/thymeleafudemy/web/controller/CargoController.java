package com.example.thymeleafudemy.web.controller;

import com.example.thymeleafudemy.domain.Cargo;
import com.example.thymeleafudemy.domain.Departamento;
import com.example.thymeleafudemy.service.CargoService;
import com.example.thymeleafudemy.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/cargos")
public class CargoController {

    @Autowired
    CargoService cargoService;

    @Autowired
    DepartamentoService departamentoService;

    @GetMapping("/cadastrar")
    public String cadastrar(Cargo cargo) {
        return "/cargo/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap modelMap) {
        modelMap.addAttribute("cargos", cargoService.buscarTodos());
        return "/cargo/lista";
    }

    @PostMapping("/salvar")
    public String salvar(Cargo cargo, RedirectAttributes attr) {
        cargoService.salvar(cargo);
        attr.addFlashAttribute("success", "Cargo inserido com sucesso");
        return "redirect:/cargos/cadastrar";
    }

    @ModelAttribute("departamentos")
    public List<Departamento> listaDeDepartamentos() {
        return departamentoService.buscarTodos();
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap modelMap) {
        modelMap.addAttribute("cargo", cargoService.buscarPorId(id));
        return "cargo/cadastro";
    }

    @PostMapping("/editar")
    public String editar(Cargo cargo, RedirectAttributes attr) {
        cargoService.editar(cargo);
        attr.addFlashAttribute("success", "Registro atualizado com sucesso");
        return "redirect:/cargos/cadastrar";
    }


    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) {

        if (cargoService.cargoTemFuncionarios(id)) {
            model.addAttribute("fail", "Cargo não excluído. Tem funcionário(s) vinculado(s).");
        } else {
            cargoService.excluir(id);
            model.addAttribute("success", "Cargo excluído com sucesso.");
        }

        return listar(model);
    }

}
