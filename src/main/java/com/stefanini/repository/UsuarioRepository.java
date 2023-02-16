package com.stefanini.repository;

import javax.enterprise.context.ApplicationScoped;

import com.stefanini.dao.GenericDAO;
import com.stefanini.entity.Usuario;

@ApplicationScoped
public class UsuarioRepository extends GenericDAO<Usuario,Long>{
    
}
