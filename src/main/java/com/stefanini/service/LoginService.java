package com.stefanini.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;



import com.stefanini.entity.Login;
import com.stefanini.repository.UsuarioRepository;


@ApplicationScoped
public class LoginService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    UsuarioService usuarioService;
    
    public Boolean authenticate(Login login) throws Exception{
        return usuarioService.authenticate(login);
    }
}
