package com.projeto.servlet;

import com.projeto.model.Descricao;
import com.projeto.model.Remedio;
import com.projeto.util.JPAUtil;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import jakarta.persistence.EntityManager;

import java.io.IOException;

@WebServlet("/teste")
public class TesteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

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
        r.setNome("Dipirona");
        r.setBula("https://bula.com/dipirona");
        r.setDescricao(d);

        em.getTransaction().begin();

        em.persist(d); // salva descrição primeiro
        em.persist(r); // depois remédio

        em.getTransaction().commit();
        em.close();

        resp.getWriter().println("Salvo com relacionamento!");
    }
}