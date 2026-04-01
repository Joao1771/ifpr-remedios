package com.projeto;

import com.projeto.model.Descricao;
import com.projeto.model.Remedio;
import com.projeto.util.JPAUtil;

import jakarta.persistence.EntityManager;

public class TesteJPA {

    public static void main(String[] args) {

        EntityManager em = JPAUtil.getEntityManager();

        // Criar descrição
        Descricao d = new Descricao();
        d.setPublicoAlvo("Adultos");
        d.setPrecaucoes("Nenhuma");
        d.setContraIndicacoes("Nenhuma");
        d.setComposicao("Composição X");
        d.setEfeitos("Efeito Y");

        // Criar remédio
        Remedio r = new Remedio();
        r.setNome("Paracetamol");
        r.setBula("https://bula.com/paracetamol");
        r.setDescricao(d);

        em.getTransaction().begin();

        em.persist(d); // salva descrição primeiro
        em.flush();
        em.persist(r); // depois remédio

        em.getTransaction().commit();
        em.close();

        System.out.println("Salvo com relacionamento!");
    }
}