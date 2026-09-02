package br.edu.ifpr.OfflineApp.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

//atribuindo o nome "T" a essa classe genérica
public class DAO<T> {

	private static DAO instance;
	protected EntityManager em;
 
	/*
	 * GERAÇÃO DA INSTÂNCIA DO MEU PROJETO
	 * UTILIZAÇÃO DO PADRÃO SINGLETON
	 * 
	 * @return instância
	 */
	public static DAO getInstance() {
		if (instance == null) {
			instance = new DAO();
		}
		return instance;
	}
 
	public DAO(){
		em = getEntityManager();
	}
 
	/*
	 * CONEXÃO COM O BANCO DE DADOS
	 * UTILIZAÇÃO DO PADRÃO FACTORY
	 */
	private EntityManager getEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Remedio");
		if (em == null) {
			em = emf.createEntityManager();
	    }
	    return em;
	}
	 
	/*CRUDE*/
	//salvar (criar)
	public void save(T object) {
		try {
			em.getTransaction().begin();
			em.persist(object);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}
	//update
	public void update(T object) {
		try {
			em.getTransaction().begin();
			em.merge(object);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}
	//delete
	public void delete(T object) {
		try {
			em.getTransaction().begin();
			em.remove(object);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
	}
	
	//limpa o entity manager para que possa ser ocupado por outros objetos de valores diferentes
	public void clear() {
	    em.clear();
	}
}
