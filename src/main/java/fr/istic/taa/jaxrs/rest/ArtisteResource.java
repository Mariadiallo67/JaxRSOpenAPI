package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Artiste;
import fr.istic.taa.jaxrs.service.ArtisteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/artistes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Artiste")
public class ArtisteResource {

    @GET
    public Response findAll() {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            return Response.ok(new ArtisteService(em).findAll()).build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            Artiste artiste = new ArtisteService(em).findById(id);
            if (artiste == null) return Response.status(Response.Status.NOT_FOUND).build();
            return Response.ok(artiste).build();
        } finally {
            em.close();
        }
    }

    @POST
    public Response create(Artiste artiste) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(new ArtisteService(em).create(artiste))
                    .build();
        } finally {
            em.close();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Artiste artiste) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            ArtisteService service = new ArtisteService(em);
            if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
            artiste.setId(id);
            return Response.ok(service.update(artiste)).build();
        } finally {
            em.close();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            ArtisteService service = new ArtisteService(em);
            if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
            service.delete(id);
            return Response.noContent().build();
        } finally {
            em.close();
        }
    }
}