package com.projeto.servlet;

import com.google.gson.Gson;
import com.projeto.dao.RemedioDAO;
import com.projeto.dto.RemedioDTO;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/api/remedios")
public class RemedioServlet extends HttpServlet {

    private RemedioDAO dao = new RemedioDAO();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String idParam = req.getParameter("id");

        if (idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                System.out.println("ID recebido: " + id);

                RemedioDTO r = dao.buscarPorId(id);

                if (r == null) {
                    resp.setStatus(404);
                    resp.getWriter().print("{\"erro\":\"Não encontrado\"}");
                    return;
                }

                resp.getWriter().print(gson.toJson(r));

            } catch (NumberFormatException e) {
                resp.setStatus(400);
                resp.getWriter().print("{\"erro\":\"ID inválido\"}");
            }

        } else {
            resp.getWriter().print(gson.toJson(dao.listar()));
        }
    }
}