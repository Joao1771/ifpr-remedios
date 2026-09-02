package com.projeto.dao;

import java.util.List;

import com.projeto.dto.EmpresaDTO;
import com.projeto.model.Empresa;
import com.projeto.util.JPAUtil;

import jakarta.persistence.EntityManager;

public class EmpresaDAO extends DAO<Empresa> {

    public EmpresaDAO() {
        super(Empresa.class);
    }
    
    private EmpresaDTO converterParaDTO(Empresa e) {
       EmpresaDTO dto = new EmpresaDTO();

       dto.id = e.getId();
       dto.nome = e.getNome();
       dto.cnpj = e.getCnpj();

       if (e.getCidade() != null) {
           dto.cidade = e.getCidade().getNome();
           dto.uf = e.getCidade().getUf();
       }

       return dto;
   }

    public List<EmpresaDTO> buscarPorNome(String nome) {

       EntityManager em = JPAUtil.getEntityManager();

       try {

           if (nome == null) {
               nome = "";
           }

           return em.createQuery("""
                   SELECT e
                   FROM Empresa e
                   LEFT JOIN FETCH e.cidade
                   WHERE LOWER(e.nome)
                         LIKE LOWER(:nome)
                   ORDER BY e.nome
                   """, Empresa.class)
                   .setParameter("nome", "%" + nome + "%")
                   .setMaxResults(8)
                   .getResultList()
                   .stream()
                   .map(this::converterParaDTO)
                   .toList();

       } finally {
           em.close();
       }
   }

}