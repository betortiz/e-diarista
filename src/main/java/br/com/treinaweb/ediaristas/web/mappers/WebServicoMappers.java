package br.com.treinaweb.ediaristas.web.mappers;

import org.springframework.stereotype.Component;

import br.com.treinaweb.ediaristas.core.models.Servico;
import br.com.treinaweb.ediaristas.web.dtos.ServicoForm;

@Component
public class WebServicoMappers {
    
    public Servico toModel(ServicoForm form){
        if (form == null) {
            throw new IllegalArgumentException("Formulário não pode ser nulo");
        }

        var model = new Servico();

        model.setNome(form.getNome());
        model.setValorMinimo(form.getValorMinimo());
        model.setQtdHoras(form.getQtdHoras());
        model.setPorcentagemComissao(form.getPorcentagemComissao());
        model.setHorasQuarto(form.getHorasQuarto());
        model.setValorQuarto(form.getValorQuarto());
        model.setHorasSala(form.getHorasSala());
        model.setValorSala(form.getValorSala());
        model.setHorasCozinha(form.getHorasCozinha());
        model.setValorCozinha(form.getValorCozinha());
        model.setHorasBanheiro(form.getHorasBanheiro());
        model.setValorBanheiro(form.getValorBanheiro());
        model.setHorasQuintal(form.getHorasQuintal());
        model.setValorQuintal(form.getValorQuintal());
        model.setHorasOutros(form.getHorasOutros());
        model.setValorOutros(form.getValorOutros());
        model.setIcone(form.getIcone());
        model.setPosicao(form.getPosicao());

        return model;
    }

    public ServicoForm toForm (Servico model){
        if (model == null) {
            throw new IllegalArgumentException("Modelo não pode ser nulo");
        }

        var form = new ServicoForm();

        form.setValorMinimo(model.getValorMinimo());
        form.setNome(model.getNome());
        form.setQtdHoras(model.getQtdHoras());
        form.setPorcentagemComissao(model.getPorcentagemComissao());
        form.setHorasQuarto(model.getHorasQuarto());
        form.setValorQuarto(model.getValorQuarto());
        form.setHorasSala(model.getHorasSala());
        form.setValorSala(model.getValorSala());
        form.setHorasCozinha(model.getHorasCozinha());
        form.setValorCozinha(model.getValorCozinha());
        form.setHorasBanheiro(model.getHorasBanheiro());
        form.setValorBanheiro(model.getValorBanheiro());
        form.setHorasQuintal(model.getHorasQuintal());
        form.setValorQuintal(model.getValorQuintal());
        form.setHorasOutros(model.getHorasOutros());
        form.setValorOutros(model.getValorOutros());
        form.setIcone(model.getIcone());
        form.setPosicao(model.getPosicao());
        
        return form;
    }
}
