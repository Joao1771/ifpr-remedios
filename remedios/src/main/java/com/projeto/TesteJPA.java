package com.projeto;

import com.projeto.model.*;
import com.projeto.util.JPAUtil;

import jakarta.persistence.EntityManager;

public class TesteJPA {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();

        em.getTransaction().begin();

        // BUSCAR TARJA EXISTENTE
        Tarjas t = em.find(Tarjas.class, 2); // Vermelha

        // BUSCAR PÚBLICO ALVO EXISTENTE
        PublicoAlvo pa = em.find(PublicoAlvo.class, 2); // Adultos

        // PRESCRIÇÃO
        Prescricao p = new Prescricao();
        p.setPublicoAlvo("Adultos");
        p.setRestricao("Somente com receita");
        p.setContraIndicacoes("Casos de suspeita de Dengue");
        p.setComposicao("Composição X");
        p.setEfeitos("Tontura e sono");
        p.setValidade("24 meses");
        p.setConservacao("Ambiente seco e frio");

        // REMÉDIO
        Remedio r = new Remedio();
        r.setNome("Paracetamol");
        r.setBula("https://bula.com/paracetamol");
        r.setTipo("G");
        r.setPrescricao(p);
        r.setTarjas(t);

        // CIDADE
        Cidade c = new Cidade();
        c.setNome("Dois Vizinhos");
        c.setUf("PR");

        // EMPRESA
        Empresa e = new Empresa();
        e.setNome("Farmacia Central");
        e.setCnpj("99999999000199");
        e.setCidade(c);

        // SUBSTÂNCIA
        Substancia s = new Substancia();
        s.setNome("Paracetamol Base");
        s.setTipo("Química");

        // SALVAR ENTIDADES
        em.persist(p);
        em.persist(r);

        em.persist(c);
        em.persist(e);

        em.persist(s);

        em.flush();

        // RELACIONAMENTO REMEDIO ↔ EMPRESA
        EmpresaRemedio er = new EmpresaRemedio();
        er.setEmpresa(e);
        er.setRemedio(r);
        em.persist(er);

        // RELACIONAMENTO REMEDIO ↔ SUBSTANCIA
        SubstanciaRemedio sr = new SubstanciaRemedio();
        sr.setRemedio(r);
        sr.setSubstancia(s);
        em.persist(sr);

        // RELACIONAMENTO REMEDIO ↔ PUBLICO_ALVO
        RemedioPublicoAlvo rpa = new RemedioPublicoAlvo();
        rpa.setRemedio(r);
        rpa.setPublicoAlvo(pa);
        em.persist(rpa);

        em.getTransaction().commit();
        em.close();

        System.out.println("Dados salvos com sucesso!");
    }
}