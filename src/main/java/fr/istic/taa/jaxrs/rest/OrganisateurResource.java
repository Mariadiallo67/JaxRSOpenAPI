package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Organisateur;
import fr.istic.taa.jaxrs.service.OrganisateurService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/organisateurs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Organisateur")
public class OrganisateurResource {

    @GET
    public Response findAll() {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            return Response.ok(new OrganisateurService(em).findAll()).build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            Organisateur organisateur = new OrganisateurService(em).findById(id);
            if (organisateur == null) return Response.status(Response.Status.NOT_FOUND).build();
            return Response.ok(organisateur).build();
        } finally {
            em.close();
        }
    }

    @POST
    public Response create(Organisateur organisateur) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(new OrganisateurService(em).create(organisateur))
                    .build();
        } finally {
            em.close();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Organisateur organisateur) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            OrganisateurService service = new OrganisateurService(em);
            if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
            organisateur.setId(id);
            return Response.ok(service.update(organisateur)).build();
        } finally {
            em.close();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            OrganisateurService service = new OrganisateurService(em);
            if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
            service.delete(id);
            return Response.noContent().build();
        } finally {
            em.close();
        }
    }
}