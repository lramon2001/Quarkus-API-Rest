package com.stefanini.mappers;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.stefanini.dtos.UsuarioRequest;
import com.stefanini.dtos.UsuarioResponse;
import com.stefanini.entity.Usuario;

@ApplicationScoped
public class UsuarioMapperImpl implements UsuarioMapperInterface {

    @Override
    public UsuarioResponse toUsuarioResponse(Usuario usuario) {
        
        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setId(usuario.getId());
        usuarioResponse.setNome(usuario.getNome());
        usuarioResponse.setLogin(usuario.getLogin());
        usuarioResponse.setEmail(usuario.getEmail());
        usuarioResponse.setDataNascimento(usuario.getDataNascimento());
        usuarioResponse.setDataCriacao(usuario.getDataCriacao());
        usuarioResponse.setDataAtualizacao(usuario.getDataAtualizacao());
        
        return usuarioResponse;
    }

    @Override
    public Usuario toUsuarioEntity(UsuarioRequest usuario) {
        Usuario usuarioEntity = new Usuario();
        usuarioEntity.setId(usuario.getId());
        usuarioEntity.setNome(usuario.getNome());
        usuarioEntity.setLogin(usuario.getLogin());
        usuarioEntity.setEmail(usuario.getEmail());
        usuarioEntity.setSenha(usuario.getSenha());
        usuarioEntity.setDataNascimento(usuario.getDataNascimento());
        usuarioEntity.setDataCriacao(usuario.getDataCriacao());
        usuarioEntity.setDataAtualizacao(usuario.getDataAtualizacao());
        usuarioEntity.setSenha(usuario.getSenha());
        return usuarioEntity;
    }

    public List<UsuarioResponse> toListOfUsuarioResponse(List<Usuario> usuariosEntityList) {
        List<UsuarioResponse> usuariosResponseList = new ArrayList<UsuarioResponse>();
        usuariosEntityList.forEach(usuarioEntity -> {
            usuariosResponseList.add(toUsuarioResponse(usuarioEntity));
        });
        return usuariosResponseList;
    }
    
}
