package br.edu.ifpr.OfflineApp.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.OfflineApp.model.Remedio;
import br.edu.ifpr.OfflineApp.model.Substancia;
import jakarta.persistence.Query;

public class SubstanciaDAO extends DAO<Substancia> {
	
	public List<Substancia> findByRelation(Remedio remedio) {
		int idRemedioSelecionado = remedio.getId();
		List<Substancia> entidadesSubstancia;
		try {
		    Query query = em.createQuery("SELECT s " + "FROM Substancia s, SubstanciaRemedio sr, Remedio r " +  
		    "WHERE sr.substancia.id=s.id AND sr.remedio.id = :idRemedioSelecionado",Substancia.class);
		    query.setParameter("idRemedioSelecionado", remedio.getId());
		    entidadesSubstancia = query.getResultList();
		    return entidadesSubstancia;
		}catch (Exception e) {
			e.printStackTrace();
	        return new ArrayList<>();
		}
	}
}
