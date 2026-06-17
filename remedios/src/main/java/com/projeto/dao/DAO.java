package com.projeto.dao;

import java.util.List;

import com.projeto.util.JPAUtil;

import jakarta.persistence.EntityManager;

// modelo DAO genérico.
public abstract class DAO<T> {

    private Class<T> classe;

    public DAO(Class<T> classe) {
        this.classe = classe;
    }

    public void salvar(T entidade) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(entidade);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void atualizar(T entidade) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(entidade);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void deletar(int id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            T entidade = em.find(classe, id);

            if (entidade != null) {
                em.getTransaction().begin();
                em.remove(entidade);
                em.getTransaction().commit();
            }

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public T buscarPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        T entidade = null;

        try {
            entidade = em.find(classe, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return entidade;
    }

    public List<T> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        List<T> lista = null;

        try {
            lista = em.createQuery("FROM " + classe.getSimpleName(), classe)
                      .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return lista;
    }
}