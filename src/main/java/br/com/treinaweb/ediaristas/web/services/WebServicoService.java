package br.com.treinaweb.ediaristas.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.treinaweb.ediaristas.core.exceptions.servicoNaoEncontradoException;
import br.com.treinaweb.ediaristas.core.models.Servico;
import br.com.treinaweb.ediaristas.core.repositories.ServicoRepository;
import br.com.treinaweb.ediaristas.web.dtos.ServicoForm;
import br.com.treinaweb.ediaristas.web.mappers.WebServicoMappers;

@Service
public class WebServicoService {

    // injeção de dependência do repository
    @Autowired
    private ServicoRepository repository;

    // injeção de dependência do mapper
    @Autowired
    private WebServicoMappers mapper;
    
    // método para buscar todos os serviços
    public List<Servico> buscarTodos() {
        return repository.findAll();
    }

    // método para cadastrar um serviço
    public Servico cadastrar(ServicoForm form) {

        var model = mapper.toModel(form);

        return repository.save(model);
    }

    // método para buscar um serviço por id
    public Servico buscarPorId(Long id) {

        var servicoEncontrado = repository.findById(id);

        if (servicoEncontrado.isPresent()) {
            return servicoEncontrado.get();
        }

        var mensagem = String.format("Serviço com ID %d não encontrado", id);
        throw new servicoNaoEncontradoException(mensagem);
    }

    // método para editar um serviço por id
    public Servico editar(ServicoForm form, Long id) {

        var servicoEncontrado = buscarPorId(id);

        var model = mapper.toModel(form);
        model.setId(servicoEncontrado.getId());

        return repository.save(model);
    }

    // método para excluir um serviço por id
    public void excluirPorId(Long id) {

        var servicoEncontrado = buscarPorId(id);

        repository.delete(servicoEncontrado);
    }
}
