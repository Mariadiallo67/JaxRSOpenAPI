package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.dto.TicketDTO;
import fr.istic.taa.jaxrs.dto.mapper.DtoMapper;
import fr.istic.taa.jaxrs.service.TicketService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/tickets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Ticket")
public class TicketResource {

    @GET
    public Response findAll() {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            TicketService service = new TicketService(em);
            List<TicketDTO> result = service.findAll()
                    .stream()
                    .map(DtoMapper::toTicketDTO)
                    .collect(Collectors.toList());

            return Response.ok(result).build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            TicketService service = new TicketService(em);
            Ticket ticket = service.findById(id);

            if (ticket == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.ok(DtoMapper.toTicketDTO(ticket)).build();
        } finally {
            em.close();
        }
    }

    @POST
    public Response create(Ticket ticket) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            TicketService service = new TicketService(em);
            Ticket created = service.create(ticket);

            return Response.status(Response.Status.CREATED)
                    .entity(DtoMapper.toTicketDTO(created))
                    .build();
        } finally {
            em.close();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Ticket ticket) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            TicketService service = new TicketService(em);

            Ticket existing = service.findById(id);
            if (existing == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            ticket.setId(id);
            Ticket updated = service.update(ticket);

            return Response.ok(DtoMapper.toTicketDTO(updated)).build();
        } finally {
            em.close();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            TicketService service = new TicketService(em);

            Ticket existing = service.findById(id);
            if (existing == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            service.delete(id);
            return Response.noContent().build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/proprietaire/{clientId}")
    public Response findByProprietaire(@PathParam("clientId") Long clientId) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            TicketService service = new TicketService(em);

            List<TicketDTO> result = service.ticketsParProprietaire(clientId)
                    .stream()
                    .map(DtoMapper::toTicketDTO)
                    .collect(Collectors.toList());

            return Response.ok(result).build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/categorie/{categorieId}")
    public Response findByCategorie(@PathParam("categorieId") Long categorieId) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            TicketService service = new TicketService(em);

            List<TicketDTO> result = service.ticketsParCategorie(categorieId)
                    .stream()
                    .map(DtoMapper::toTicketDTO)
                    .collect(Collectors.toList());

            return Response.ok(result).build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/code/{codeQR}")
    public Response findByCodeQR(@PathParam("codeQR") String codeQR) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            TicketService service = new TicketService(em);
            Ticket ticket = service.findByCodeQR(codeQR);

            if (ticket == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.ok(DtoMapper.toTicketDTO(ticket)).build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/valides/client/{clientId}")
    public Response findTicketsValidesByClient(@PathParam("clientId") Long clientId) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            TicketService service = new TicketService(em);

            List<TicketDTO> result = service.ticketsValidesParClient(clientId)
                    .stream()
                    .map(DtoMapper::toTicketDTO)
                    .collect(Collectors.toList());

            return Response.ok(result).build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/transferes")
    public Response findTicketsTransferes() {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            TicketService service = new TicketService(em);

            List<TicketDTO> result = service.ticketsTransferes()
                    .stream()
                    .map(DtoMapper::toTicketDTO)
                    .collect(Collectors.toList());

            return Response.ok(result).build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/evenement/{evenementId}/count")
    public Response compterTicketsVendusPourEvenement(@PathParam("evenementId") Long evenementId) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            TicketService service = new TicketService(em);
            long count = service.compterTicketsVendusPourEvenement(evenementId);

            return Response.ok(count).build();
        } finally {
            em.close();
        }
    }

    @PUT
    @Path("/{id}/utiliser")
    public Response marquerCommeUtilise(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            TicketService service = new TicketService(em);
            Ticket ticket = service.marquerCommeUtilise(id);

            if (ticket == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.ok(DtoMapper.toTicketDTO(ticket)).build();
        } finally {
            em.close();
        }
    }

    @PUT
    @Path("/{id}/transferer/{clientId}")
    public Response transfererTicket(@PathParam("id") Long id,
                                     @PathParam("clientId") Long clientId) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            TicketService service = new TicketService(em);
            Ticket ticket = service.transfererTicket(id, clientId);

            if (ticket == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.ok(DtoMapper.toTicketDTO(ticket)).build();
        } finally {
            em.close();
        }
    }
}
