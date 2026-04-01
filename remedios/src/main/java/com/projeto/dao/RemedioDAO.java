package com.projeto.dao;

import java.util.List;

import com.projeto.model.Remedio;
import com.projeto.util.JPAUtil;

import jakarta.persistence.EntityManager;

public class RemedioDAO {

    // CREATE
    public void salvar(Remedio remedio) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(remedio);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // READ (listar todos)
    public List<Remedio> listar() {
        EntityManager em = JPAUtil.getEntityManager();

        List<Remedio> lista = null;

        try {
            lista = em.createQuery("FROM Remedio", Remedio.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return lista;
    }

    // READ (buscar por ID)
    public Remedio buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        Remedio remedio = null;

        try {
            remedio = em.find(Remedio.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return remedio;
    }

    // UPDATE
    public void atualizar(Remedio remedio) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(remedio);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // DELETE
    public void deletar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            Remedio remedio = em.find(Remedio.class, id);

            if (remedio != null) {
                em.getTransaction().begin();
                em.remove(remedio);
                em.getTransaction().commit();
            }

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}