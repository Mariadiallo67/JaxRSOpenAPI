package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Notification;
import fr.istic.taa.jaxrs.service.NotificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/notifications")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Notification")
public class NotificationResource {

    @GET
    public Response findAll() {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            return Response.ok(new NotificationService(em).findAll()).build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            Notification notification = new NotificationService(em).findById(id);
            if (notification == null) return Response.status(Response.Status.NOT_FOUND).build();
            return Response.ok(notification).build();
        } finally {
            em.close();
        }
    }

    @POST
    public Response create(Notification notification) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(new NotificationService(em).create(notification))
                    .build();
        } finally {
            em.close();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Notification notification) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            NotificationService service = new NotificationService(em);
            if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
            notification.setId(id);
            return Response.ok(service.update(notification)).build();
        } finally {
            em.close();
        }
    }

    @PUT
    @Path("/{id}/lue")
    public Response marquerCommeLue(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            NotificationService service = new NotificationService(em);
            Notification notification = service.marquerCommeLue(id);
            if (notification == null) return Response.status(Response.Status.NOT_FOUND).build();
            return Response.ok(notification).build();
        } finally {
            em.close();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            NotificationService service = new NotificationService(em);
            if (service.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
            service.delete(id);
            return Response.noContent().build();
        } finally {
            em.close();
        }
    }
}