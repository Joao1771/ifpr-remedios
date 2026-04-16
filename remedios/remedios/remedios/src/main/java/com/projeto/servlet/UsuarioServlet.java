package com.projeto.servlet;

import com.google.gson.Gson;
import com.projeto.dao.UsuarioDAO;
import com.projeto.model.Usuario;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/usuarios")
public class UsuarioServlet extends HttpServlet {

    private UsuarioDAO dao = new UsuarioDAO();
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
            Integer id = Integer.parseInt(idParam);
            Usuario u = dao.buscarPorId(id);

            if (u == null) {
                respostaErro(resp, 404, "Usuário não encontrado");
                return;
            }

            resp.getWriter().print(gson.toJson(u));

        } else {
            List<Usuario> lista = dao.listar();
            resp.getWriter().print(gson.toJson(lista));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        try {
            BufferedReader reader = req.getReader();
            Usuario u = gson.fromJson(reader, Usuario.class);

            if (u.getNome() == null || u.getSenha() == null || u.getTipo() == null) {
                respostaErro(resp, 400, "Dados incompletos");
                return;
            }

            dao.salvar(u);

            resp.setStatus(201);
            resp.getWriter().print(gson.toJson(u));

        } catch (Exception e) {
            e.printStackTrace();
            respostaErro(resp, 500, "Erro ao criar usuário");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        try {
            BufferedReader reader = req.getReader();
            Usuario u = gson.fromJson(reader, Usuario.class);

            if (u.getId() == null) {
                respostaErro(resp, 400, "ID não informado");
                return;
            }

            if (dao.buscarPorId(u.getId()) == null) {
                respostaErro(resp, 404, "Usuário não encontrado");
                return;
            }

            dao.atualizar(u);

            resp.setStatus(200);
            resp.getWriter().print("Atualizado com sucesso!");

        } catch (Exception e) {
            respostaErro(resp, 500, "Erro ao atualizar");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String idParam = req.getParameter("id");

        if (idParam == null) {
            respostaErro(resp, 400, "ID não informado");
            return;
        }

        Integer id = Integer.parseInt(idParam);

        if (dao.buscarPorId(id) == null) {
            respostaErro(resp, 404, "Usuário não encontrado");
            return;
        }

        dao.deletar(id);

        resp.setStatus(200);
        resp.getWriter().print("Deletado com sucesso!");
    }
}