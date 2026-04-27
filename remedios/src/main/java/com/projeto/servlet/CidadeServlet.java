package com.projeto.servlet;

import com.google.gson.Gson;
import com.projeto.dao.CidadeDAO;
import com.projeto.model.Cidade;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/cidades")
public class CidadeServlet extends HttpServlet {

    private CidadeDAO dao = new CidadeDAO();
    private Gson gson = new Gson();

    private void respostaErro(HttpServletResponse resp, int status, String mensagem) throws IOException {
        resp.setStatus(status);
        resp.setContentType("application/json");
        resp.getWriter().print("{\"erro\": \"" + mensagem + "\"}");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");

        String idParam = req.getParameter("id");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            Cidade c = dao.buscarPorId(id);

            if (c == null) {
                respostaErro(resp, 404, "Cidade não encontrada");
                return;
            }

            resp.getWriter().print(gson.toJson(c));
        } else {
            List<Cidade> lista = dao.listar();
            resp.getWriter().print(gson.toJson(lista));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        try {
            BufferedReader reader = req.getReader();
            Cidade c = gson.fromJson(reader, Cidade.class);

            if (c.getNome() == null) {
                respostaErro(resp, 400, "Nome obrigatório");
                return;
            }

            dao.salvar(c);

            resp.setStatus(201);
            resp.getWriter().print(gson.toJson(c));

        } catch (Exception e) {
            respostaErro(resp, 500, "Erro ao criar cidade");
        }
    }
}