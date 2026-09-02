package br.edu.ifpr.OfflineApp.dao;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifpr.OfflineApp.model.PublicoAlvo;
import br.edu.ifpr.OfflineApp.model.Remedio;
import jakarta.persistence.Query;

public class PublicoAlvoDAO extends DAO<PublicoAlvo>{

	public List<PublicoAlvo> findByRelation(Remedio remedio) {
		int idRemedioSelecionado = remedio.getId();
		List<PublicoAlvo> entidadesPublicoAlvo;
		try {
		    Query query = em.createQuery("SELECT pa " + "FROM PublicoAlvo pa, RemediosPublicoAlvo rpa, Remedio r " +  
		    "WHERE rpa.publicoAlvo.id=pa.id AND rpa.remedio.id = :idRemedioSelecionado",PublicoAlvo.class);
		    query.setParameter("idRemedioSelecionado", remedio.getId());
		    entidadesPublicoAlvo = query.getResultList();
		    return entidadesPublicoAlvo;
		}catch (Exception e) {
			e.printStackTrace();
	        return new ArrayList<>();
		}
	}
}
