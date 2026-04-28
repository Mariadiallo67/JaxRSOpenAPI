package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Transfert;
import fr.istic.taa.jaxrs.dto.mapper.DtoMapper;
import fr.istic.taa.jaxrs.service.TransfertService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.stream.Collectors;

@Path("/transferts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Transfert")
public class TransfertResource {

    @GET
    public Response findAll() {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            TransfertService service = new TransfertService(em);
            return Response.ok(service.findAll().stream()
                    .map(DtoMapper::toTransfertDTO)
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
            TransfertService service = new TransfertService(em);
            Transfert transfert = service.findById(id);
            if (transfert == null) return Response.status(Response.Status.NOT_FOUND).build();
            return Response.ok(DtoMapper.toTransfertDTO(transfert)).build();
        } finally {
            em.close();
        }
    }

    @POST
    public Response create(Transfert transfert) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            TransfertService service = new TransfertService(em);
            return Response.status(Response.Status.CREATED)
                    .entity(DtoMapper.toTransfertDTO(service.create(transfert)))
                    .build();
        } finally {
            em.close();
        }
    }

    @PUT
    @Path("/{id}/accepter")
    public Response accepter(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            Transfert transfert = new TransfertService(em).accepter(id);
            if (transfert == null) return Response.status(Response.Status.NOT_FOUND).build();
            return Response.ok(DtoMapper.toTransfertDTO(transfert)).build();
        } finally {
            em.close();
        }
    }

    @PUT
    @Path("/{id}/refuser")
    public Response refuser(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            Transfert transfert = new TransfertService(em).refuser(id);
            if (transfert == null) return Response.status(Response.Status.NOT_FOUND).build();
            return Response.ok(DtoMapper.toTransfertDTO(transfert)).build();
        } finally {
            em.close();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            TransfertService service = new TransfertService(em);
            if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
            service.delete(id);
            return Response.noContent().build();
        } finally {
            em.close();
        }
    }
}