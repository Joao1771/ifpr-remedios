package com.projeto.resource;

import java.util.List;
import com.projeto.dao.DAO;
import com.projeto.dao.RemedioDAO;
import com.projeto.dto.RemedioCadastroDTO;
import com.projeto.dto.RemedioDTO;
import com.projeto.model.*;
import com.projeto.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/remedios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RemedioResource {

    private RemedioDAO dao = new RemedioDAO();

    // LISTAR TODOS
    @GET
    public List<RemedioDTO> listar() {
        return dao.listar();
    }

    // BUSCAR POR ID
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {

        RemedioDTO dto = dao.buscarPorIdDTO(id);

        if (dto == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Remédio não encontrado")
                    .build();
        }

        return Response.ok(dto).build();
    }

    // SALVAR
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response salvar(RemedioCadastroDTO dto) {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Remedio remedio = new Remedio();

            Empresa empresa = em.find(Empresa.class, dto.idEmpresa);
            if (empresa == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Empresa não encontrada")
                        .build();
            }

            Tarja tarja = em.find(Tarja.class, dto.idTarja);
            if (tarja == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Tarja não encontrada")
                        .build();
            }

            Substancia s = new Substancia();
            s.setNome(dto.substancia);
            s.setTipo(dto.substanciaTipo);

            em.persist(s);

            Prescricao p = new Prescricao();
            p.setRestricao(dto.restricao);
            p.setContraIndicacoes(dto.contraIndicacoes);
            p.setEfeitos(dto.efeitos);
            p.setValidade(dto.validade);
            p.setConservacao(dto.conservacao);

            em.persist(p);

            remedio.setNome(dto.nome);
            remedio.setBula(dto.bula);
            remedio.setTipo(dto.tipo);

            remedio.setEmpresa(empresa);
            remedio.setTarjas(tarja);
            remedio.setSubstancia(s);
            remedio.setPrescricao(p);

            em.persist(remedio);


                for (Integer id : dto.idPublicoAlvo) {

                    PublicoAlvo pa = em.find(PublicoAlvo.class, id);

                        RemedioPublicoAlvo rpa = new RemedioPublicoAlvo();
                        rpa.setRemedio(remedio);
                        rpa.setPublicoAlvo(pa);
                        em.persist(rpa);
                    
                }

            em.getTransaction().commit();

            return Response.status(Response.Status.CREATED)
                    .entity("Remédio criado com sucesso")
                    .build();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();

        } finally {
            em.close();
        }
    }
    
    // ATUALIZAR
    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id,
                              RemedioCadastroDTO dto) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            Remedio remedio = em.find(Remedio.class, id);

            if (remedio == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Remédio não encontrado")
                        .build();
            }

            // Dados básicos
            remedio.setNome(dto.nome);
            remedio.setBula(dto.bula);
            remedio.setTipo(dto.tipo);

            // Empresa
            Empresa empresa = em.find(Empresa.class, dto.idEmpresa);
            remedio.setEmpresa(empresa);

            // Tarja
            Tarja tarja = em.find(Tarja.class, dto.idTarja);
            remedio.setTarjas(tarja);

            // Substância
            Substancia substancia = new Substancia();

            substancia.setNome(dto.substancia);
            substancia.setTipo(dto.substanciaTipo);

            em.persist(substancia);

            remedio.setSubstancia(substancia);

            // Prescrição
            Prescricao p = remedio.getPrescricao();

            if (p == null) {
                p = new Prescricao();
                em.persist(p);
                remedio.setPrescricao(p);
            }

            p.setRestricao(dto.restricao);
            p.setContraIndicacoes(dto.contraIndicacoes);
            p.setEfeitos(dto.efeitos);
            p.setValidade(dto.validade);
            p.setConservacao(dto.conservacao);

            // Remove públicos antigos
            em.createQuery("""
                DELETE FROM RemedioPublicoAlvo rpa
                WHERE rpa.remedio.id = :id
            """)
            .setParameter("id", id)
            .executeUpdate();

            // Cria os novos públicos
            if (dto.idPublicoAlvo != null) {

                for (Integer idPublico : dto.idPublicoAlvo) {

                    PublicoAlvo publico =
                            em.find(PublicoAlvo.class, idPublico);

                    if (publico != null) {

                        RemedioPublicoAlvo rpa =
                                new RemedioPublicoAlvo();

                        rpa.setRemedio(remedio);
                        rpa.setPublicoAlvo(publico);

                        em.persist(rpa);
                    }
                }
            }

            em.merge(remedio);

            em.getTransaction().commit();

            return Response.ok("Remédio atualizado").build();

        } catch (Exception e) {

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();

        } finally {
            em.close();
        }
    }

    // DELETAR
    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {

        try {

            DAO<Remedio> daoGenerico = new DAO<>(Remedio.class) {};

            daoGenerico.deletar(id);

            return Response.ok("Remédio removido").build();

        } catch (Exception e) {

            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao remover remédio")
                    .build();
        }
    }
}