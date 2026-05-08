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
        p.setPrecaucoes("Nenhuma");
        p.setContraIndicacoes("Nenhuma");
        p.setComposicao("Composição X");
        p.setEfeitos("Efeito Y");

        // REMÉDIO
        Remedio r = new Remedio();
        r.setNome("Paracetamol");
        r.setBula("https://bula.com/paracetamol");
        r.setTipo("G");
        r.setPrescricao(p);

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

        em.getTransaction().begin();

        // salvar entidades base
        em.persist(p);
        em.persist(r);

        em.persist(c);
        em.persist(e);

        em.persist(s);

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

        em.getTransaction().commit();
        em.close();

        System.out.println("Dados salvos com sucesso!");
    }
}