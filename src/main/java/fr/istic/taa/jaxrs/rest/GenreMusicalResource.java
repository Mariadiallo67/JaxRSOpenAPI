package fr.istic.taa.jaxrs.rest;


import fr.istic.taa.jaxrs.domain.GenreMusical;
import fr.istic.taa.jaxrs.service.GenreMusicalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/genres")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "GenreMusical")
public class GenreMusicalResource {

    @GET
    public Response findAll() {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            return Response.ok(new GenreMusicalService(em).findAll()).build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            GenreMusical genreMusical = new GenreMusicalService(em).findById(id);
            if (genreMusical == null) return Response.status(Response.Status.NOT_FOUND).build();
            return Response.ok(genreMusical).build();
        } finally {
            em.close();
        }
    }

    @POST
    public Response create(GenreMusical genreMusical) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(new GenreMusicalService(em).create(genreMusical))
                    .build();
        } finally {
            em.close();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, GenreMusical genreMusical) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            GenreMusicalService service = new GenreMusicalService(em);
            if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
            genreMusical.setId(id);
            return Response.ok(service.update(genreMusical)).build();
        } finally {
            em.close();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            GenreMusicalService service = new GenreMusicalService(em);
            if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
            service.delete(id);
            return Response.noContent().build();
        } finally {
            em.close();
        }
    }
}