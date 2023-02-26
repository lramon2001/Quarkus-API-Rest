package com.stefanini.mappers;

import com.stefanini.dtos.UsuarioResponse;
import com.stefanini.entity.Usuario;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.stefanini.dtos.UsuarioRequest;;

@ApplicationScoped
public interface UsuarioMapperInterface {
    
    UsuarioResponse toUsuarioResponse(Usuario usuario);

    Usuario toUsuarioEntity(UsuarioRequest usuario);

    List<UsuarioResponse> toListOfUsuarioResponse(List<Usuario> usuariosEntityList);
}
