package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Client;
import fr.istic.taa.jaxrs.dto.mapper.DtoMapper;
import fr.istic.taa.jaxrs.service.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.stream.Collectors;

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Client")
public class ClientResource {

    @GET
    public Response findAll() {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            ClientService service = new ClientService(em);
            return Response.ok(service.findAll().stream()
                    .map(DtoMapper::toClientDTO)
                    .collect(Collectors.toList())).build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            ClientService service = new ClientService(em);
            Client client = service.findById(id);
            if (client == null) return Response.status(Response.Status.NOT_FOUND).build();
            return Response.ok(DtoMapper.toClientDTO(client)).build();
        } finally {
            em.close();
        }
    }

    @POST
    public Response create(Client client) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            ClientService service = new ClientService(em);
            return Response.status(Response.Status.CREATED)
                    .entity(DtoMapper.toClientDTO(service.create(client)))
                    .build();
        } finally {
            em.close();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Client client) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            ClientService service = new ClientService(em);
            if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
            client.setId(id);
            return Response.ok(DtoMapper.toClientDTO(service.update(client))).build();
        } finally {
            em.close();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            ClientService service = new ClientService(em);
            if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
            service.delete(id);
            return Response.noContent().build();
        } finally {
            em.close();
        }
    }
}
