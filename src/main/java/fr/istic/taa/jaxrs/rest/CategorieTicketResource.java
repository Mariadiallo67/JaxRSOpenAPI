package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.CategorieTicket;
import fr.istic.taa.jaxrs.service.CategorieTicketService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/categories-tickets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "CategorieTicketResource")
public class CategorieTicketResource {

    @GET
    public Response findAll() {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            return Response.ok(new CategorieTicketService(em).findAll()).build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            CategorieTicket categorie = new CategorieTicketService(em).findById(id);
            if (categorie == null) return Response.status(Response.Status.NOT_FOUND).build();
            return Response.ok(categorie).build();
        } finally {
            em.close();
        }
    }

    @POST
    public Response create(CategorieTicket categorie) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(new CategorieTicketService(em).create(categorie))
                    .build();
        } finally {
            em.close();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, CategorieTicket categorie) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            CategorieTicketService service = new CategorieTicketService(em);
            if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
            categorie.setId(id);
            return Response.ok(service.update(categorie)).build();
        } finally {
            em.close();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            CategorieTicketService service = new CategorieTicketService(em);
            if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
            service.delete(id);
            return Response.noContent().build();
        } finally {
            em.close();
        }
    }
}