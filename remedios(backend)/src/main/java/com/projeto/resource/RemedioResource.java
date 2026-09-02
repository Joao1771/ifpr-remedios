package com.projeto.resource;

import java.util.List;
import com.projeto.dao.DAO;
import com.projeto.dao.RemedioDAO;
import com.projeto.dto.RemedioCadastroDTO;
import com.projeto.dto.RemedioDTO;
import com.projeto.model.*;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

// endpoint para CRUD Remedios
@Path("/remedios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RemedioResource {

   private RemedioDAO remedioDAO = new RemedioDAO();
   private DAO<Remedio> dao = new DAO<>(Remedio.class) {};

    //Read
    @GET
    public List<RemedioDTO> listar() {
        return remedioDAO.listar();
    }

    // Read (id)
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {

        RemedioDTO dto = remedioDAO.buscarPorId(id);

        if (dto == null) { //previne busca de id inválido
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Remédio não encontrado")
                    .build();
        }

        return Response.ok(dto).build();
    }

    // Create
    @POST
    public Response salvar(RemedioCadastroDTO dto) {
        try {
            remedioDAO.salvarDoDTO(dto); // ← única mudança
            return Response.status(Response.Status.CREATED)
                    .entity("Remédio criado com sucesso")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }
    
    // Update
    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, RemedioCadastroDTO dto) {
        try {
            remedioDAO.atualizarDoDTO(id, dto);
            return Response.ok("Remédio atualizado").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    // Delete
    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {

        try {
            dao.deletar(id);

            return Response.ok("Remédio removido").build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao remover remédio")
                    .build();
        }
    }
}