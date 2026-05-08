package com.projeto.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.projeto.dao.SubstanciaDAO;
import com.projeto.model.Substancia;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/api/substancias")
public class SubstanciaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private SubstanciaDAO dao = new SubstanciaDAO();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");

        String idParam = req.getParameter("id");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Substancia s = dao.buscarPorId(id);

            if (s == null) {
                resp.setStatus(404);
                resp.getWriter().print("{\"erro\":\"Não encontrado\"}");
                return;
            }

            resp.getWriter().print(gson.toJson(s));

        } else {
            List<Substancia> lista = dao.listar();
            resp.getWriter().print(gson.toJson(lista));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        BufferedReader reader = req.getReader();
        Substancia s = gson.fromJson(reader, Substancia.class);

        dao.salvar(s);

        resp.setStatus(201);
        resp.getWriter().print(gson.toJson(s));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        BufferedReader reader = req.getReader();
        Substancia s = gson.fromJson(reader, Substancia.class);

        dao.atualizar(s);

        resp.setStatus(200);
        resp.getWriter().print("{\"msg\":\"Atualizado\"}");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String idParam = req.getParameter("id");

        if (idParam == null) {
            resp.setStatus(400);
            return;
        }

        int id = Integer.parseInt(idParam);

        dao.deletar(id);

        resp.setStatus(200);
        resp.getWriter().print("{\"msg\":\"Deletado\"}");
    }
}