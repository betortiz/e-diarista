package br.com.treinaweb.ediaristas.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.treinaweb.ediaristas.core.exceptions.SenhasNaoConferemException;
import br.com.treinaweb.ediaristas.web.dtos.FlashMessage;
import br.com.treinaweb.ediaristas.web.dtos.UsuarioCadastroForm;
import br.com.treinaweb.ediaristas.web.dtos.UsuarioEdicaoForm;
import br.com.treinaweb.ediaristas.web.services.WebUsuarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/usuarios")
public class UsuarioController {

    @Autowired
    private WebUsuarioService service;

    // Renderiza a tela de listagem de usuários
    @GetMapping
    public ModelAndView buscarTodos() {
        var modelAndView = new ModelAndView("admin/usuario/lista");

        modelAndView.addObject("usuarios", service.buscarTodos());

        return modelAndView;
    }

    // Renderiza a tela de cadastro de usuário
    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        var modelAndView = new ModelAndView("admin/usuario/cadastro-form");

        modelAndView.addObject("cadastroForm", new UsuarioCadastroForm());

        return modelAndView;
    }

    // Cadastra um novo usuário
    @PostMapping("/cadastrar")
    public String cadastrar(@Valid @ModelAttribute("cadastroForm") UsuarioCadastroForm cadastroForm,
            BindingResult result, RedirectAttributes attrs) {
        if (result.hasErrors()) { // Verifica se há erros de validação
            return "admin/usuario/cadastro-form"; // Retorna para a tela de cadastro
        }

        try {
            service.cadastrar(cadastroForm); // Cadastra o usuário
            attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Usuário cadastrado com sucesso!")); // Adiciona uma mensagem de sucesso
        } catch (SenhasNaoConferemException e) {
            result.addError(e.getFieldError()); // Adiciona o erro de validação no BindingResult
            return "admin/usuario/cadastro-form"; // Retorna para a tela de cadastro
        }

        return "redirect:/admin/usuarios";
    }

    // Renderiza a tela de edição de usuário
    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        var modelAndView = new ModelAndView("admin/usuario/edicao-form"); // Renderiza a tela de edição

        modelAndView.addObject("edicaoForm", service.buscarFormPorId(id)); // Busca o usuário pelo ID e adiciona no modelAndView
        
        return modelAndView;
    }

    // Edita um usuário
    @PostMapping("/{id}/editar")
    public String editar(@Valid @ModelAttribute("edicaoForm") UsuarioEdicaoForm edicaoForm, BindingResult result,
            @PathVariable Long id, RedirectAttributes attrs) {
        if (result.hasErrors()) {
            return "admin/usuario/edicao-form";
        }

        service.editar(edicaoForm, id);

        attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Usuário editado com sucesso!"));

        return "redirect:/admin/usuarios";
    }

    // Exclui um usuário
    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes attrs) {
        service.excluirPorId(id);

        attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Usuário excluído com sucesso!"));

        return "redirect:/admin/usuarios";
    }
}
