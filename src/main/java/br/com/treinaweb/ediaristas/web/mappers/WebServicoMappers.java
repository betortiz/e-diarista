package br.com.treinaweb.ediaristas.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.treinaweb.ediaristas.core.models.Servico;
import br.com.treinaweb.ediaristas.web.dtos.ServicoForm;

@Mapper(componentModel = "spring")
public interface WebServicoMappers {

    WebServicoMappers INSTANCE = Mappers.getMapper(WebServicoMappers.class);
    
    @Mapping(target = "id", ignore = true)
    Servico toModel(ServicoForm form);

    ServicoForm toForm (Servico model);
}
