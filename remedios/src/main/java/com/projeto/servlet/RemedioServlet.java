package com.projeto.servlet;

import com.google.gson.Gson;
import com.projeto.dao.RemedioDAO;
import com.projeto.model.Descricao;
import com.projeto.model.Remedio;
import com.projeto.model.Usuario;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/remedios")
public class RemedioServlet extends HttpServlet {

    private RemedioDAO dao = new RemedioDAO();
    private Gson gson = new Gson();
    
    private void respostaErro(HttpServletResponse resp, int status, String mensagem) throws IOException {
       resp.setStatus(status);
       resp.setContentType("application/json");
       resp.getWriter().print("{\"erro\": \"" + mensagem + "\"}");
   }

    // GET → listar ou buscar por id
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");
        String idParam = req.getParameter("id");

        if (idParam != null) {
            Integer id = Integer.parseInt(idParam);
            Remedio r = dao.buscarPorId(id);

            if (r == null) {
                respostaErro(resp, 404, "Remédio não encontrado");
                return;
            }

            resp.getWriter().print(gson.toJson(r));

        } else {
            List<Remedio> lista = dao.listar();
            resp.getWriter().print(gson.toJson(lista));
        }
    }

    // POST → criar
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("application/json");

        try {
            BufferedReader reader = req.getReader();
            Remedio r = gson.fromJson(reader, Remedio.class);

            // validação básica
            if (r.getNome() == null || r.getBula() == null || r.getDescricao() == null) {
                resp.setStatus(400);
                resp.getWriter().print("Dados incompletos!");
                return;
            }

            var em = com.projeto.util.JPAUtil.getEntityManager();

            em.getTransaction().begin();

            // salva descrição primeiro
            em.persist(r.getDescricao());
            
            if (r.getUsuario() != null) {
               Integer userId = r.getUsuario().getId();

               if (userId == null || userId == 0) {
                   r.setUsuario(null);
               } else {
                   var usuario = em.find(Usuario.class, userId);

                   if (usuario == null) {
                       throw new RuntimeException("Usuário não existe");
                   }

                   r.setUsuario(usuario);
               }
           }
            
            // associa e salva remédio
            em.persist(r);

            em.getTransaction().commit();
            em.close();

            resp.setStatus(201);
            resp.getWriter().print(gson.toJson(r));

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            resp.getWriter().print("Erro ao criar remédio");
        }
    }

    // PUT → atualizar
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        try {
            BufferedReader reader = req.getReader();
            Remedio r = gson.fromJson(reader, Remedio.class);

            if (r.getId() == null) {
                resp.setStatus(400);
                resp.getWriter().print("ID não informado");
                return;
            }

            var em = com.projeto.util.JPAUtil.getEntityManager();

            em.getTransaction().begin();

            // busca o existente
            Remedio existente = em.find(Remedio.class, r.getId());

            if (existente == null) {
                resp.setStatus(404);
                resp.getWriter().print("Remédio não encontrado");
                return;
            }

            // atualiza campos do remédio
            existente.setNome(r.getNome());
            existente.setBula(r.getBula());

            // atualiza descrição
            if (existente.getDescricao() != null && r.getDescricao() != null) {

                existente.getDescricao().setPublicoAlvo(r.getDescricao().getPublicoAlvo());
                existente.getDescricao().setPrecaucoes(r.getDescricao().getPrecaucoes());
                existente.getDescricao().setContraIndicacoes(r.getDescricao().getContraIndicacoes());
                existente.getDescricao().setComposicao(r.getDescricao().getComposicao());
                existente.getDescricao().setEfeitos(r.getDescricao().getEfeitos());
            }

            em.getTransaction().commit();
            em.close();

            resp.setStatus(200);
            resp.getWriter().print("Atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            resp.getWriter().print("Erro ao atualizar");
        }
    }

    // DELETE → deletar
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String idParam = req.getParameter("id");

        if (idParam == null) {
            respostaErro(resp, 400, "ID não informado");
            return;
        }

        Integer id = Integer.parseInt(idParam);
        Remedio existente = dao.buscarPorId(id);

        if (existente == null) {
            respostaErro(resp, 404, "Remédio não encontrado");
            return;
        }

        dao.deletar(id);

        resp.setStatus(200);
        resp.getWriter().print("Deletado com sucesso!");
    }
}