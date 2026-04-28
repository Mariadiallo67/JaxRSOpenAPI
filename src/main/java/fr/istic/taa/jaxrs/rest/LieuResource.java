package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Lieu;
import fr.istic.taa.jaxrs.service.LieuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/lieux")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Lieu")
public class LieuResource {

    @GET
    public Response findAll() {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            return Response.ok(new LieuService(em).findAll()).build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            Lieu lieu = new LieuService(em).findById(id);
            if (lieu == null) return Response.status(Response.Status.NOT_FOUND).build();
            return Response.ok(lieu).build();
        } finally {
            em.close();
        }
    }

    @POST
    public Response create(Lieu lieu) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(new LieuService(em).create(lieu))
                    .build();
        } finally {
            em.close();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Lieu lieu) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            LieuService service = new LieuService(em);
            if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
            lieu.setId(id);
            return Response.ok(service.update(lieu)).build();
        } finally {
            em.close();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            LieuService service = new LieuService(em);
            if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
            service.delete(id);
            return Response.noContent().build();
        } finally {
            em.close();
        }
    }
}