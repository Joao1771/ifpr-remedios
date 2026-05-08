package com.projeto.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.projeto.dao.EmpresaDAO;
import com.projeto.dao.EmpresaRemedioDAO;
import com.projeto.dao.RemedioDAO;
import com.projeto.dto.RemedioDTO;
import com.projeto.model.Empresa;
import com.projeto.model.EmpresaRemedio;
import com.projeto.model.Remedio;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/empresas-remedios")
public class EmpresaRemedioServlet extends HttpServlet {

    private EmpresaRemedioDAO dao = new EmpresaRemedioDAO();
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
        List<EmpresaRemedio> lista = dao.listar();
        resp.getWriter().print(gson.toJson(lista));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws IOException {

      try {
         
          BufferedReader reader = req.getReader();
          JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
          EmpresaDAO empresaDAO = new EmpresaDAO();
          RemedioDAO remedioDAO = new RemedioDAO();
          EmpresaRemedioDAO empresaRemedioDAO = new EmpresaRemedioDAO();

          if (!json.has("idEmpresa") || !json.has("idRemedio")) {
             respostaErro(resp, 400, "IDs obrigatórios");
             return;
         }
          
          int idEmpresa = json.get("idEmpresa").getAsInt();
          int idRemedio = json.get("idRemedio").getAsInt();
          
          Empresa empresa = empresaDAO.buscarPorId(idEmpresa);
          Remedio remedio = remedioDAO.buscarEntidadePorId(idRemedio);

          if (empresa == null || remedio == null) {
              respostaErro(resp, 404, "Empresa ou Remedio não encontrado");
              return;
          }

          EmpresaRemedio er = new EmpresaRemedio();
          er.setEmpresa(empresa);
          er.setRemedio(remedio);
          
          

          empresaRemedioDAO.salvar(er);

          resp.setStatus(201);
          resp.getWriter().print(gson.toJson(er));

      } catch (Exception ex) {
          respostaErro(resp, 500, "Erro ao criar relação");
      }
  }
}