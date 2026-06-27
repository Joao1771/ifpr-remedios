package br.edu.ifpr.OfflineApp.dao;

import java.util.List;

import br.edu.ifpr.OfflineApp.model.Remedio;
import jakarta.persistence.Query;

public class RemedioDAO extends DAO<Remedio> {
	public List<Remedio> findByName(String name) {
		List<Remedio> remedios;
		try {
		    Query query = em.createQuery("SELECT r FROM Remedio r LEFT JOIN FETCH r.prescricao WHERE r.nome LIKE :name",Remedio.class);
		    query.setParameter("name", "%"+name+"%");
		    remedios = query.getResultList();
		    return remedios;
		}catch (Exception e) {
			return null;
		}
	}
}