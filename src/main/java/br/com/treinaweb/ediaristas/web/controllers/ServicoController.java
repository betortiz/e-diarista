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

import br.com.treinaweb.ediaristas.core.enums.Icone;
import br.com.treinaweb.ediaristas.web.dtos.FlashMessage;
import br.com.treinaweb.ediaristas.web.dtos.ServicoForm;
import br.com.treinaweb.ediaristas.web.services.WebServicoService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/servicos")
public class ServicoController {

    // injeção de dependência do service
    @Autowired
    private WebServicoService service;

    // método para buscar todos os serviços e retornar a view
    @GetMapping
    public ModelAndView buscarTodos() {
        ModelAndView modelAndView = new ModelAndView("admin/servico/lista");

        modelAndView.addObject("servicos", service.buscarTodos());

        return modelAndView;
    }

    // método para cadastrar um serviço e retornar a view
    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("admin/servico/form");

        modelAndView.addObject("form", new ServicoForm());

        return modelAndView;
    }

    // método para cadastrar um serviço e retornar a view
    @PostMapping("/cadastrar")
    public String cadastrar(@Valid @ModelAttribute("form") ServicoForm form, BindingResult result,
            RedirectAttributes attrs) {

        if (result.hasErrors()) {
            return "admin/servico/form";
        }

        service.cadastrar(form);
        attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Serviço cadastrado com sucesso!"));

        return "redirect:/admin/servicos";
    }

    // método para editar um serviço e retornar a view
    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/servico/form");

        modelAndView.addObject("form", service.buscarPorId(id));

        return modelAndView;
    }

    // método para editar um serviço e retornar a view
    @PostMapping("/{id}/editar")
    public String editar(@PathVariable Long id, @Valid @ModelAttribute("form") ServicoForm form, BindingResult result,
            RedirectAttributes attrs) {

        if (result.hasErrors()) {
            return "admin/servico/form";
        }

        service.editar(form, id);
        attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Serviço editado com sucesso!"));

        return "redirect:/admin/servicos";
    }

    // método para excluir um serviço e retornar a view
    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes attrs) {
        service.excluirPorId(id);
        attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Serviço excluído com sucesso!"));

        return "redirect:/admin/servicos";
    }

    // método para retornar os ícones
    @ModelAttribute("icones")
    public Icone[] getIcones() {
        return Icone.values();
    }
}
