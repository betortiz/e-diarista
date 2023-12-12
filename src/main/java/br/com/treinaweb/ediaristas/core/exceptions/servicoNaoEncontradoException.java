package br.com.treinaweb.ediaristas.core.exceptions;

import jakarta.persistence.EntityNotFoundException;

// classe para tratar a exceção de serviço não encontrado
public class servicoNaoEncontradoException extends EntityNotFoundException {
    
    // construtor que recebe uma mensagem e repassa para a classe pai
    public servicoNaoEncontradoException(String message) {
        super(message);
    }
}
