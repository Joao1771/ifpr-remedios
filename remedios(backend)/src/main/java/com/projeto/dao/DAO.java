package com.projeto.dao;

import java.util.List;

import com.projeto.util.JPAUtil;

import jakarta.persistence.EntityManager;

// classe DAO abstrata genérica 
public abstract class DAO<T> {

    private Class<T> classe;

    public DAO(Class<T> classe) {
        this.classe = classe;
    }
    //CREATE
    public void salvar(T entidade) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin(); //inicia a transação
            em.persist(entidade);//insere a entidade no banco
            em.getTransaction().commit();// commita e encerra
        } catch (Exception e) {
            em.getTransaction().rollback();//caso de errado volta
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    //UPDATE
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
    //DELETE
    public void deletar(int id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            T entidade = em.find(classe, id);//busca pelo id

            if (entidade != null) {
                em.getTransaction().begin();
                em.remove(entidade);// remove o registro
                em.getTransaction().commit();
            }

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    //READ (find)
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
    //READ
    public List<T> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        List<T> lista = null;

        try {
           // mostra uma consulta JPQL
            lista = em.createQuery("FROM " + classe.getSimpleName(), classe).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return lista;
    }
}