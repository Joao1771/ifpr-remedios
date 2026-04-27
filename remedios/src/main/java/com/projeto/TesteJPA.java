package com.projeto;

import com.projeto.model.Cidade;
import com.projeto.model.Descricao;
import com.projeto.model.Empresa;
import com.projeto.model.Remedio;
import com.projeto.util.JPAUtil;

import jakarta.persistence.EntityManager;

public class TesteJPA {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();


        Descricao d = new Descricao();
        d.setPublicoAlvo("Adultos");
        d.setPrecaucoes("Nenhuma");
        d.setContraIndicacoes("Nenhuma");
        d.setComposicao("Composição X");
        d.setEfeitos("Efeito Y");

        Remedio r = new Remedio();
        r.setNome("Paracetamol");
        r.setBula("https://bula.com/paracetamol");
        r.setDescricao(d);


        Cidade c = new Cidade();
        c.setNome("Dois Vizinhos");
        c.setUf("PR");

        Empresa e = new Empresa();
        e.setNome("Farmacia Central");
        e.setCnpj("12345678000199");
        e.setCidade(c);


        em.getTransaction().begin();

        // salva descrição e remédio
        em.persist(d);
        em.flush();
        em.persist(r);

        // salva cidade e empresa
        em.persist(c);
        em.flush();
        em.persist(e);

        em.getTransaction().commit();
        em.close();

        System.out.println("Dados salvos com sucesso!");
    }
}