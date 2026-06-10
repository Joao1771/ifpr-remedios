package com.projeto.resource;

import java.util.List;
import java.util.stream.Collectors;

import com.projeto.dao.EmpresaDAO;
import com.projeto.dto.EmpresaDTO;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/empresas")
@Produces(MediaType.APPLICATION_JSON)
public class EmpresaResource {

    private EmpresaDAO dao = new EmpresaDAO();

    @GET
    @Path("/busca")
    public List<EmpresaDTO> buscarPorNome(
            @QueryParam("nome") String nome) {

        return dao.buscarPorNome(nome)
                .stream()
                .map(e -> {

                    EmpresaDTO dto = new EmpresaDTO();

                    dto.id = e.getId();
                    dto.nome = e.getNome();
                    dto.cnpj = e.getCnpj();

                    if (e.getCidade() != null) {
                        dto.cidade = e.getCidade().getNome();
                        dto.uf = e.getCidade().getUf();
                    }

                    return dto;
                })
                .collect(Collectors.toList());
    }
}