package com.projeto.resource;

import java.util.List;

import com.projeto.dao.UsuarioDAO;
import com.projeto.dto.LoginDTO;
import com.projeto.dto.UsuarioCadastroDTO;
import com.projeto.dto.UsuarioDTO;
import com.projeto.model.Usuario;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

// endpoint para criar (cadastrar), listar e logar usuários
@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    private UsuarioDAO dao = new UsuarioDAO();

    @GET
    public List<UsuarioDTO> listar() {
        return dao.listarDTO();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {

        UsuarioDTO dto = dao.buscarPorIdDTO(id);

        if (dto == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado").build();
        }

        return Response.ok(dto).build();
    }

    @POST
    public Response salvar(UsuarioCadastroDTO dto) {

        try {

            Usuario usuario = new Usuario();

            usuario.setEmail(dto.email);
            usuario.setSenha(dto.senha);
            usuario.setTipo(dto.tipo);

            dao.salvar(usuario);

            return Response.status(Response.Status.CREATED).entity("Usuário criado com sucesso").build();

        } catch (Exception e) {

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
    
    @POST
    @Path("/login")
    public Response login(LoginDTO dto) {

        try {

            UsuarioDTO usuario = dao.autenticar(dto.email, dto.senha);

            if (usuario == null) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Email ou senha inválidos").build();
            }

            return Response.ok(usuario).build();

        } catch (Exception e) {

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}