package com.projeto.dao;

import java.util.ArrayList;
import java.util.List;
import com.projeto.dto.RemedioDTO;
import com.projeto.dto.SubstanciaDTO;
import com.projeto.model.Remedio;
import com.projeto.model.SubstanciaRemedio;
import com.projeto.util.JPAUtil;

import jakarta.persistence.EntityManager;

public class RemedioDAO {

    public List<RemedioDTO> listar() {

        EntityManager em = JPAUtil.getEntityManager();
        List<RemedioDTO> dtoList = new ArrayList<>();

        try {
            List<Remedio> lista = em.createQuery("""
                SELECT DISTINCT r FROM Remedio r
               LEFT JOIN FETCH r.prescricao	
               LEFT JOIN FETCH r.substanciasRemedios sr
               LEFT JOIN FETCH sr.substancia
            """, Remedio.class).getResultList();

            for (Remedio r : lista) {

               RemedioDTO dto = new RemedioDTO();

               dto.id = r.getId();
               dto.nome = r.getNome();
               dto.bula = r.getBula();
               dto.tipo = r.getTipo();

               // Prescrição
               if (r.getPrescricao() != null) {
                   dto.publicoAlvo = r.getPrescricao().getPublicoAlvo();
                   dto.restricao = r.getPrescricao().getRestricao();
                   dto.contraIndicacoes = r.getPrescricao().getContraIndicacoes();
                   dto.composicao = r.getPrescricao().getComposicao();
                   dto.efeitos = r.getPrescricao().getEfeitos();
               }

               // Substâncias (já carregadas com JOIN FETCH)
               if (r.getSubstanciasRemedios() != null) {
                  dto.substancias = r.getSubstanciasRemedios()
                        .stream()
                        .map((SubstanciaRemedio sr) -> new SubstanciaDTO(sr.getSubstancia()))
                        .collect(java.util.stream.Collectors.toList());
               }

               // Empresas (SEM JOIN FETCH → cuidado com LAZY)
               if (r.getEmpresasRemedios() != null) {
                   dto.empresas = r.getEmpresasRemedios()
                       .stream()
                       .map(er -> er.getEmpresa().getNome())
                       .toList();
               }

               dtoList.add(dto);
           }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return dtoList;
    }
    public RemedioDTO buscarPorIdDTO(int id) {

       EntityManager em = JPAUtil.getEntityManager();
       RemedioDTO dto = null;

       try {
           Remedio r = em.createQuery("""
               SELECT r FROM Remedio r
               LEFT JOIN FETCH r.prescricao
               LEFT JOIN FETCH r.substanciasRemedios sr
               LEFT JOIN FETCH sr.substancia
               LEFT JOIN FETCH r.empresasRemedios er
               LEFT JOIN FETCH er.empresa
               WHERE r.id = :id
           """, Remedio.class)
           .setParameter("id", id)
           .getSingleResult();

           dto = new RemedioDTO();

           dto.id = r.getId();
           dto.nome = r.getNome();
           dto.bula = r.getBula();
           dto.tipo = String.valueOf(r.getTipo());

           // Prescrição
           if (r.getPrescricao() != null) {
               dto.publicoAlvo = r.getPrescricao().getPublicoAlvo();
               dto.restricao = r.getPrescricao().getRestricao();
               dto.contraIndicacoes = r.getPrescricao().getContraIndicacoes();
               dto.composicao = r.getPrescricao().getComposicao();
               dto.efeitos = r.getPrescricao().getEfeitos();
           }

           // Substâncias
           if (r.getSubstanciasRemedios() != null) {
              dto.substancias = r.getSubstanciasRemedios()
                    .stream()
                    .map(sr -> new SubstanciaDTO(sr.getSubstancia()))
                    .collect(java.util.stream.Collectors.toList());
           }

           // Empresas
           if (r.getEmpresasRemedios() != null) {
               dto.empresas = r.getEmpresasRemedios()
                   .stream()
                   .map(er -> er.getEmpresa().getNome())
                   .toList();
           }

       } catch (Exception e) {
           dto = null;
       } finally {
           em.close();
       }

       return dto;
   }
    public RemedioDTO buscarPorId(int id) {

       EntityManager em = JPAUtil.getEntityManager();

       try {
           Remedio r = em.createQuery("""
               SELECT r FROM Remedio r
               LEFT JOIN FETCH r.prescricao
               LEFT JOIN FETCH r.substanciasRemedios sr
               LEFT JOIN FETCH sr.substancia
               LEFT JOIN FETCH r.empresasRemedios er
               LEFT JOIN FETCH er.empresa
               WHERE r.id = :id
           """, Remedio.class)
           .setParameter("id", id)
           .getSingleResult();
           
           System.out.println("Buscando no banco ID: " + id);

           RemedioDTO dto = new RemedioDTO();

           dto.id = r.getId();
           dto.nome = r.getNome();
           dto.bula = r.getBula();
           dto.tipo = String.valueOf(r.getTipo());
           
           

           if (r.getPrescricao() != null) {
               dto.publicoAlvo = r.getPrescricao().getPublicoAlvo();
               dto.restricao = r.getPrescricao().getRestricao();
               dto.contraIndicacoes = r.getPrescricao().getContraIndicacoes();
               dto.composicao = r.getPrescricao().getComposicao();
               dto.efeitos = r.getPrescricao().getEfeitos();
           }

           return dto;

       } catch (Exception e) {
           e.printStackTrace();
           return null;
       } finally {
           em.close();
       }
   }
    public Remedio buscarEntidadePorId(int id) {
       EntityManager em = JPAUtil.getEntityManager();

       try {
           return em.find(Remedio.class, id);
       } catch (Exception e) {
           return null;
       } finally {
           em.close();
       }
   }
}

