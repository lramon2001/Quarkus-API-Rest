package com.stefanini.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.stefanini.entity.ProvedorEmail;
import com.stefanini.entity.Usuario;
import com.stefanini.repository.UsuarioRepository;
import com.stefanini.utils.EmailUtils;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    public Usuario findById(Long id) throws Exception {
        Usuario usuario = usuarioRepository.findById(id);
        if (Objects.isNull(usuario)) {
            throw new Exception("Usuario não encontrado");
        }
        return usuario;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.listAll();
    }

    public Usuario create(Usuario usuario) {
        usuario.setDataCriacao(LocalDateTime.now());
        usuarioRepository.save(usuario);
        return usuario;
    }

    public void delete(Long id) {
        usuarioRepository.delete(id);
    }

    public Usuario update(Usuario usuarioNovo, Long id) {
        Usuario usuario = usuarioRepository.findById(id);
        usuario.setNome(usuarioNovo.getNome());
        usuario.setLogin(usuarioNovo.getLogin());
        usuario.setEmail(usuarioNovo.getEmail());
        usuario.setSenha(usuarioNovo.getSenha());
        usuario.setDataNascimento(usuarioNovo.getDataNascimento());
        usuario.setDataAtualizacao(LocalDateTime.now());
        usuarioRepository.update(usuario);
        return usuario;
    }

    public List<Usuario> listBirthDays(int mounth) {
        Month mes = Month.of(mounth);
        String query = "SELECT u FROM Usuario u WHERE MONTH(u.dataNascimento) = :mesAtual";
        List<Usuario> usuarios = usuarioRepository.createSimpleQuery(query)
            .setParameter("mesAtual", mes.getValue())
            .getResultList();
        return usuarios;
    }

    public List<Usuario> listByName(String name) {
        String query = "SELECT u FROM Usuario u WHERE u.nome LIKE :nome";
        List<Usuario> usuarios = usuarioRepository.createSimpleQuery(query)
                .setParameter("nome", "%" + name + "%")
                .getResultList();

        return usuarios;
    }

    public Usuario findByLogin(String login) {
        String query = "SELECT u FROM Usuario u WHERE u.login = :login";
        Usuario usuario = (Usuario) usuarioRepository.createSimpleQuery(query)
                .setParameter("login", login)
                .getSingleResult();
        return usuario;
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

}
