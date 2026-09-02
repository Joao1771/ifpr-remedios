package com.projeto.dao;

import java.util.List;

import com.projeto.dto.RemedioCadastroDTO;
import com.projeto.dto.RemedioDTO;
import com.projeto.model.Remedio;
import com.projeto.util.JPAUtil;
import com.projeto.model.*;

import jakarta.persistence.EntityManager;

public class RemedioDAO {

    // Converte entidade → DTO de leitura
    private RemedioDTO converterParaDTO(Remedio r) {
        RemedioDTO dto = new RemedioDTO();
        dto.id = r.getId();
        dto.nome = r.getNome();
        dto.bula = r.getBula();
        dto.tipo = String.valueOf(r.getTipo());
        dto.restricao = r.getPrescricao().getRestricao();
        dto.contraIndicacoes = r.getPrescricao().getContraIndicacoes();
        dto.efeitos = r.getPrescricao().getEfeitos();
        dto.validade = r.getPrescricao().getValidade();
        dto.conservacao = r.getPrescricao().getConservacao();
        dto.tarja = r.getTarjas().getNome();
        
        // simplifica a relação N:N de RemediosPublicoAlvo para uma lista
        dto.publicoAlvo = r.getRemediosPublicoAlvo().stream()
        		.map(rpa -> rpa.getPublicoAlvo().getNome())
                .toList();
        
        dto.substancia = r.getSubstancia().getNome();
        dto.substanciaTipo = r.getSubstancia().getTipo();
        dto.empresa = r.getEmpresa().getNome();
        dto.cnpj = r.getEmpresa().getCnpj();
        dto.cidade = r.getEmpresa().getCidade().getNome();
        dto.uf = r.getEmpresa().getCidade().getUf();
        return dto;
    }

    // Converte DTO de cadastro → entidade (requer EntityManager ativo para buscar relacionamentos)
    private void aplicarDadosDTO(Remedio remedio, RemedioCadastroDTO dto, EntityManager em) {
        remedio.setNome(dto.nome);
        remedio.setBula(dto.bula);
        remedio.setTipo(dto.tipo);
        remedio.setEmpresa(em.find(Empresa.class, dto.idEmpresa));
        remedio.setTarjas(em.find(Tarja.class, dto.idTarja));
        remedio.getPrescricao().setRestricao(dto.restricao);
        remedio.getPrescricao().setContraIndicacoes(dto.contraIndicacoes);
        remedio.getPrescricao().setEfeitos(dto.efeitos);
        remedio.getPrescricao().setValidade(dto.validade);
        remedio.getPrescricao().setConservacao(dto.conservacao);
        remedio.getSubstancia().setNome(dto.substancia);
        remedio.getSubstancia().setTipo(dto.substanciaTipo);
    }

    private Remedio novoRemedioComDTO(RemedioCadastroDTO dto, EntityManager em) {
        Remedio remedio = new Remedio();
        remedio.setPrescricao(new Prescricao());
        remedio.setSubstancia(new Substancia());
        aplicarDadosDTO(remedio, dto, em);
        return remedio;
    }
    
    public void salvarDoDTO(RemedioCadastroDTO dto) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Remedio remedio = novoRemedioComDTO(dto, em);
            em.persist(remedio);
            em.flush();

            for (Integer idPublico : dto.idPublicoAlvo) {
                RemedioPublicoAlvo rpa = new RemedioPublicoAlvo();
                rpa.setRemedio(remedio);
                rpa.setPublicoAlvo(em.find(PublicoAlvo.class, idPublico));
                em.persist(rpa);
            }

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        } finally {
            em.close();
        }
    }

    public void atualizarDoDTO(int id, RemedioCadastroDTO dto) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Remedio remedio = em.find(Remedio.class, id);
            aplicarDadosDTO(remedio, dto, em);

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<RemedioDTO> listar() {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery("""
                    SELECT DISTINCT r
                    FROM Remedio r
                    LEFT JOIN FETCH r.prescricao
                    LEFT JOIN FETCH r.tarja
                    LEFT JOIN FETCH r.remediosPublicoAlvo rpa
                    LEFT JOIN FETCH rpa.publicoAlvo
                    LEFT JOIN FETCH r.substancia
                    LEFT JOIN FETCH r.empresa e
                    LEFT JOIN FETCH e.cidade
                    """, Remedio.class)
                    .getResultList().stream()
                    .map(this::converterParaDTO)
                    .toList();
        } finally {
            em.close();
        }
    }
    
    

    public RemedioDTO buscarPorId(int id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            Remedio r = em.createQuery("""
                    SELECT r
                    FROM Remedio r
                    LEFT JOIN FETCH r.prescricao
                    LEFT JOIN FETCH r.tarja
                    LEFT JOIN FETCH r.remediosPublicoAlvo rpa
                    LEFT JOIN FETCH rpa.publicoAlvo
                    LEFT JOIN FETCH r.substancia
                    LEFT JOIN FETCH r.empresa e
                    LEFT JOIN FETCH e.cidade
                    WHERE r.id = :id
                    """, Remedio.class)
                    .setParameter("id", id)
                    .getSingleResult();

            return converterParaDTO(r);

        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
  

