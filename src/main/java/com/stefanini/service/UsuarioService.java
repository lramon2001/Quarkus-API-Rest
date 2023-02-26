package com.stefanini.service;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stefanini.dtos.UsuarioRequest;
import com.stefanini.dtos.UsuarioResponse;
import com.stefanini.entity.Login;
import com.stefanini.entity.ProvedorEmail;
import com.stefanini.entity.Usuario;
import com.stefanini.enums.Mes;
import com.stefanini.enums.MesEnum;
import com.stefanini.repository.UsuarioRepository;
import com.stefanini.utils.EmailUtils;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    public Usuario findById(Long id) throws Exception {
        Usuario usuario = usuarioRepository.findById(id);
        if (Objects.isNull(usuario)) {
            logger.error(" O Usuario de id: "+id+" não foi encontrado");
            throw new RuntimeException();
        }
        return usuario;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.listAll();
    }

    public UsuarioResponse create(UsuarioRequest usuario) {
        return usuarioRepository.save(usuario);
    }

    public void delete(Long id) {
        try{
        usuarioRepository.delete(id);
        }catch(Exception e){
            logger.error("Erro ao deletar usuario de id: "+id, e);
            throw new RuntimeException();
        }
    }

    public UsuarioResponse update(UsuarioRequest usuarioNovo, Long id) {
        return  usuarioRepository.update(usuarioNovo, id);
    }

    public List<UsuarioResponse> listBirthDays(int mounth) {
        return usuarioRepository.listBirthDays(mounth);
    }

    public List<UsuarioResponse> listByName(String name) {
        return usuarioRepository.listByName(name);
    }

    public UsuarioResponse findByLogin(String login) {
        return usuarioRepository.findByLogin(login);
    }

    public boolean authenticate(Login login) throws Exception {
     
        UsuarioResponse usuario = findByLogin(login.getLogin());
        Usuario usuarioEntity = this.findById(usuario.getId());
        
        if (Objects.isNull(usuario)) {
            logger.info(" O Usuario de login: "+login.getLogin()+" não foi encontrado");
            throw new RuntimeException();
        }
        return usuarioEntity.getSenha().equals(login.getSenha());
    }

  
    public Set<ProvedorEmail> findAllEmailproviders(){
        Set<ProvedorEmail> dominios = new HashSet<ProvedorEmail>();
        List<Usuario> usuarios = this.findAll();
        usuarios.forEach(usuario->{
            String dominioEmail = EmailUtils.extrairDominioEmail(usuario.getEmail());
            dominios.add(new ProvedorEmail(dominioEmail));
        });
        return dominios;
    }
    public List<Mes> findAllMonths() {
        List<Mes> meses = new ArrayList<Mes>();
        List<MesEnum> mesesEnum = List.of(MesEnum.values());
        mesesEnum.forEach(mesEnum->{
           meses.add(new Mes(mesEnum.getNumero(), mesEnum.getDescricao()));
        });
        return meses;
    }

    

}
