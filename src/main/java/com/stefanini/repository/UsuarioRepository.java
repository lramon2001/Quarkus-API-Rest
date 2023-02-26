package com.stefanini.repository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.stefanini.dao.GenericDAO;
import com.stefanini.dtos.UsuarioRequest;
import com.stefanini.dtos.UsuarioResponse;
import com.stefanini.entity.Usuario;
import com.stefanini.mappers.UsuarioMapperInterface;


@ApplicationScoped
public class UsuarioRepository extends GenericDAO<Usuario, Long> {
    @Inject
    UsuarioMapperInterface usuarioMapper;

    public UsuarioResponse save(UsuarioRequest usuarioRequest) {

        usuarioRequest.setDataAtualizacao(LocalDateTime.now());
        usuarioRequest.setDataCriacao(LocalDateTime.now());

        Usuario usuarioEntity = usuarioMapper.toUsuarioEntity(usuarioRequest);
        this.save(usuarioMapper.toUsuarioEntity(usuarioRequest));

        return this.usuarioMapper.toUsuarioResponse(usuarioEntity);
    }

    public UsuarioResponse update(UsuarioRequest usuarioRequest,Long id) {
        Usuario usuarioEntity= this.findById(id);
        usuarioEntity.setNome(usuarioRequest.getNome());
        usuarioEntity.setLogin(usuarioRequest.getLogin());
        usuarioEntity.setEmail(usuarioRequest.getEmail());
        usuarioEntity.setSenha(usuarioRequest.getSenha());
        usuarioEntity.setDataNascimento(usuarioRequest.getDataNascimento());
        usuarioEntity.setDataAtualizacao(LocalDateTime.now());
        this.update(usuarioEntity);
        return this.usuarioMapper.toUsuarioResponse(usuarioEntity);
    }

    public List<UsuarioResponse> listBirthDays(int mounth){
        Month mes = Month.of(mounth);
        String query = "SELECT u FROM Usuario u WHERE MONTH(u.dataNascimento) = :mesAtual";
        List<Usuario> usuarios = this.createSimpleQuery(query)
            .setParameter("mesAtual", mes.getValue())
            .getResultList();
        return this.usuarioMapper.toListOfUsuarioResponse(usuarios);  
    }

    public List<UsuarioResponse> listByName(String name){
        String query = "SELECT u FROM Usuario u WHERE u.nome LIKE :nome";
        List<Usuario> usuarios = this.createSimpleQuery(query)
                .setParameter("nome", "%" + name + "%")
                .getResultList();
        return this.usuarioMapper.toListOfUsuarioResponse(usuarios);
    }

    public UsuarioResponse findByLogin(String login) {
 
        CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);

        Root<Usuario> root =  criteriaQuery.from(Usuario.class);
        
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("login"), login));
        
        TypedQuery<Usuario> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
        Usuario usuario = typedQuery.getSingleResult();

        return this.usuarioMapper.toUsuarioResponse(usuario);
    }

}
