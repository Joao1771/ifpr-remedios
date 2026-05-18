package com.projeto;

import com.projeto.model.*;
import com.projeto.util.JPAUtil;

import jakarta.persistence.EntityManager;

public class TesteJPA {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();

        // PRESCRIÇÃO
        Prescricao p = new Prescricao();
        p.setPublicoAlvo("Adultos");
        p.setRestricao("Nenhuma");
        p.setContraIndicacoes("Nenhuma");
        p.setComposicao("Composição X");
        p.setEfeitos("Efeito Y");
        p.setValidade("24 meses");

        // TARJA
        Tarjas t = new Tarjas();
        t.setNome("Tarja Vermelha");

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
        e.setCnpj("12345678000199");
        e.setCidade(c);

        // SUBSTÂNCIA
        Substancia s = new Substancia();
        s.setNome("Paracetamol Base");
        s.setTipo("Química");

        // PÚBLICO ALVO
        PublicoAlvo pa = new PublicoAlvo();
        pa.setNome("Adultos");

        em.getTransaction().begin();

        // salvar entidades base
        em.persist(p);
        em.persist(t);
        em.persist(r);

        em.persist(c);
        em.persist(e);

        em.persist(s);

        em.persist(pa);

        em.flush(); // garante IDs

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