package com.projeto.servlet;

import com.google.gson.Gson;
import com.projeto.dao.EmpresaDAO;
import com.projeto.model.Empresa;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/empresa")
public class EmpresaServlet extends HttpServlet {

    private EmpresaDAO dao = new EmpresaDAO();
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
            Empresa e = dao.buscarPorId(id);

            if (e == null) {
                respostaErro(resp, 404, "Empresa não encontrada");
                return;
            }

            resp.getWriter().print(gson.toJson(e));
        } else {
            List<Empresa> lista = dao.listar();
            resp.getWriter().print(gson.toJson(lista));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        try {
            BufferedReader reader = req.getReader();
            Empresa e = gson.fromJson(reader, Empresa.class);

            if (e.getNome() == null || e.getCnpj() == null) {
                respostaErro(resp, 400, "Dados incompletos");
                return;
            }

            dao.salvar(e);

            resp.setStatus(201);
            resp.getWriter().print(gson.toJson(e));

        } catch (Exception ex) {
            respostaErro(resp, 500, "Erro ao criar empresa");
        }
    }
}