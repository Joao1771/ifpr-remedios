package com.projeto.dao;

import java.util.List;

import com.projeto.model.Empresa;
import com.projeto.util.JPAUtil;

import jakarta.persistence.EntityManager;

public class EmpresaDAO extends DAO<Empresa> {

    public EmpresaDAO() {
        super(Empresa.class);
    }

       public List<Empresa> buscarPorNome(String nome) {

          EntityManager em = JPAUtil.getEntityManager();

          try {

              return em.createQuery("""
                  SELECT e
                  FROM Empresa e
                  LEFT JOIN FETCH e.cidade
                  WHERE e.nome LIKE :nome
                  ORDER BY e.nome
              """, Empresa.class)
              .setParameter("nome", "%" + nome + "%")
              .setMaxResults(8)
              .getResultList();

          } finally {
              em.close();
          }
      }
}