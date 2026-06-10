package com.projeto;

import com.projeto.model.*;
import com.projeto.util.JPAUtil;

import jakarta.persistence.EntityManager;

public class TesteJPA {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();

        // BUSCAR TARJA EXISTENTE
        Tarja t = em.find(Tarja.class, 2); // Vermelha

        // BUSCAR PÚBLICO ALVO EXISTENTE
        PublicoAlvo pa = em.find(PublicoAlvo.class, 2); // Adultos

        // PRESCRIÇÃO
        Prescricao p = new Prescricao();
        p.setRestricao("Somente com receita");
        p.setContraIndicacoes("Casos de suspeita de Dengue");
        p.setEfeitos("Tontura e sono");
        p.setValidade("24 meses");
        p.setConservacao("Ambiente seco e frio");

        // SUBSTÂNCIA
        Substancia s = new Substancia();
        s.setNome("Paracetamol Base");
        s.setTipo("Química");

        // REMÉDIO
        Remedio r = new Remedio();
        r.setNome("Paracetamol");
        r.setBula("bulas/bula_exemplo.pdf");
        r.setTipo("G");
        r.setPrescricao(p);
        r.setTarjas(t);
        r.setSubstancia(s);

        // CIDADE
        Cidade c = new Cidade();
        c.setNome("Dois Vizinhos");
        c.setUf("PR");

        // EMPRESA
        Empresa e = em.find(Empresa.class, 1);

        r.setEmpresa(e);

        // SALVAR ENTIDADES
        em.persist(p);
        em.persist(s);
        em.persist(r);

        em.persist(c);
        em.persist(e);

        em.flush();

        // RELACIONAMENTO REMEDIO ↔ PUBLICO ALVO
        RemedioPublicoAlvo rpa = new RemedioPublicoAlvo();
        rpa.setRemedio(r);
        rpa.setPublicoAlvo(pa);
        em.persist(rpa);

        em.getTransaction().commit();
        em.close();

        System.out.println("Dados salvos com sucesso!");
    }
}