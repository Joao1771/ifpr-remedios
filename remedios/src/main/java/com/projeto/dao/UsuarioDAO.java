package com.projeto.dao;

import java.util.List;

import com.projeto.model.Usuario;
import com.projeto.util.JPAUtil;

import jakarta.persistence.EntityManager;

public class UsuarioDAO {

    public List<Usuario> listar() {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                "FROM Usuario",
                Usuario.class
            ).getResultList();

        } finally {
            em.close();
        }
    }

    public Usuario buscarPorId(int id) {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.find(Usuario.class, id);

        } finally {
            em.close();
        }
    }

    public void salvar(Usuario usuario) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.persist(usuario);

            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            throw e;

        } finally {
            em.close();
        }
    }
    
    public Usuario autenticar(String email, String senha) {

       EntityManager em = JPAUtil.getEntityManager();

       try {

           return em.createQuery("""
               SELECT u
               FROM Usuario u
               WHERE u.email = :email
               AND u.senha = :senha
           """, Usuario.class)
           .setParameter("email", email)
           .setParameter("senha", senha)
           .getSingleResult();

       } catch (Exception e) {

           return null;

       } finally {
           em.close();
       }
   }
}