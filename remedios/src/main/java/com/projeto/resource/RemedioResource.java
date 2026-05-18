package com.projeto.resource;

import java.util.List;
import com.projeto.dao.DAO;
import com.projeto.dao.RemedioDAO;
import com.projeto.dto.RemedioDTO;
import com.projeto.model.Remedio;
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
    public Response salvar(Remedio remedio) {

        try {

            DAO<Remedio> daoGenerico = new DAO<>(Remedio.class) {};

            daoGenerico.salvar(remedio);

            return Response
                    .status(Response.Status.CREATED)
                    .entity("Remédio salvo com sucesso")
                    .build();

        } catch (Exception e) {

            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar remédio")
                    .build();
        }
    }

    // ATUALIZAR
    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id,
                              Remedio remedio) {

        try {

            remedio.setId(id);

            DAO<Remedio> daoGenerico = new DAO<>(Remedio.class) {};

            daoGenerico.atualizar(remedio);

            return Response.ok("Remédio atualizado").build();

        } catch (Exception e) {

            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar remédio")
                    .build();
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