package br.com.treinaweb.ediaristas.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import br.com.treinaweb.ediaristas.core.enums.TipoUsuario;
import br.com.treinaweb.ediaristas.core.exceptions.SenhasNaoConferemException;
import br.com.treinaweb.ediaristas.core.exceptions.UsuarioNaoEncontradoException;
import br.com.treinaweb.ediaristas.core.models.Usuario;
import br.com.treinaweb.ediaristas.core.repositories.UsuarioRepository;
import br.com.treinaweb.ediaristas.web.dtos.UsuarioCadastroForm;
import br.com.treinaweb.ediaristas.web.dtos.UsuarioEdicaoForm;
import br.com.treinaweb.ediaristas.web.mappers.WebUsuarioMapper;

@Service
public class WebUsuarioService {

    // Injeta o repositório
    @Autowired
    private UsuarioRepository repository;

    // Injeta o mapper
    @Autowired
    private WebUsuarioMapper mapper;

    // Busca todos os usuários cadastrados
    public List<Usuario> buscarTodos() {
        return repository.findAll();
    }

    // Cadastra um novo usuário
    public Usuario cadastrar(UsuarioCadastroForm form) {

        var senha = form.getSenha();
        var confirmacaoSenha = form.getConfirmacaoSenha();

        if (!senha.equals(confirmacaoSenha)) {
            var menssagem = "As senhas não conferem";
            var fieldError = new FieldError(form.getClass().getName(), "confirmacaoSenha", form.getConfirmacaoSenha(),
                    false, null, null, menssagem);

            throw new SenhasNaoConferemException(menssagem, fieldError);
        }

        var model = mapper.toModel(form);

        model.setTipoUsuario(TipoUsuario.ADMIN);

        return repository.save(model);
    }

    // Busca um usuário pelo ID
    public Usuario buscarPorId(Long id) {

        var menssagem = String.format("Usuário com ID %d não encontrado", id);

        return repository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(menssagem));
    }

    // Renderiza a pagina Atualiza um usuário
    public UsuarioEdicaoForm buscarFormPorId(Long id) {
        var usuario = buscarPorId(id);

        return mapper.toForm(usuario);
    }

    // Atualiza um usuário
    public Usuario editar(UsuarioEdicaoForm form, Long id) {

        var usuario = buscarPorId(id);

        var model = mapper.toModel(form);

        model.setId(usuario.getId());
        model.setSenha(usuario.getSenha());
        model.setTipoUsuario(usuario.getTipoUsuario());

        return repository.save(model);
    }

    // Exclui um usuário
    public void excluirPorId(Long id) {

        var usuario = buscarPorId(id);

        repository.delete(usuario);
    }
}