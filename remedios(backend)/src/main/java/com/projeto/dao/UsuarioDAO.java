package com.projeto.dao;

import java.util.List;

import com.projeto.dto.UsuarioDTO;
import com.projeto.model.Usuario;
import com.projeto.util.JPAUtil;

import jakarta.persistence.EntityManager;

public class UsuarioDAO extends DAO<Usuario> {

   public UsuarioDAO() {
       super(Usuario.class);
   }

   private UsuarioDTO converterParaDTO(Usuario u) {
       UsuarioDTO dto = new UsuarioDTO();

       dto.id = u.getId();
       dto.email = u.getEmail();
       dto.tipo = u.getTipo();

       return dto;
   }

   public List<UsuarioDTO> listarDTO() {
       return super.listar().stream().map(this::converterParaDTO).toList();
   }

   public UsuarioDTO buscarPorIdDTO(int id) {
       Usuario u = super.buscarPorId(id);

       return u != null ? converterParaDTO(u) : null;
   }

   public UsuarioDTO autenticar(String email, String senha) {

       EntityManager em = JPAUtil.getEntityManager();

       try {
           Usuario usuario = em.createQuery("""
                   SELECT u
                   FROM Usuario u
                   WHERE u.email = :email
                   AND u.senha = :senha
                   """, Usuario.class).setParameter("email", email).setParameter("senha", senha).getSingleResult();

           return converterParaDTO(usuario);

       } catch (Exception e) {
           return null;
       } finally {
           em.close();
       }
   }
}