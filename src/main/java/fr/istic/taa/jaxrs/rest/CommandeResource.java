package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Commande;
import fr.istic.taa.jaxrs.domain.enumeration.StatutCommande;
import fr.istic.taa.jaxrs.dto.CommandeDTO;
import fr.istic.taa.jaxrs.dto.mapper.DtoMapper;
import fr.istic.taa.jaxrs.service.CommandeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Path("/commandes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Commande")
public class CommandeResource {

    @GET
    public Response findAll() {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            CommandeService service = new CommandeService(em);

            List<CommandeDTO> result = service.findAll()
                    .stream()
                    .map(DtoMapper::toCommandeDTO)
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
            CommandeService service = new CommandeService(em);
            Commande commande = service.findById(id);

            if (commande == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.ok(DtoMapper.toCommandeDTO(commande)).build();
        } finally {
            em.close();
        }
    }

    @POST
    public Response create(Commande commande) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            CommandeService service = new CommandeService(em);
            Commande created = service.create(commande);

            return Response.status(Response.Status.CREATED)
                    .entity(DtoMapper.toCommandeDTO(created))
                    .build();
        } finally {
            em.close();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Commande commande) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            CommandeService service = new CommandeService(em);

            Commande existing = service.findById(id);
            if (existing == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            commande.setId(id);
            Commande updated = service.update(commande);

            return Response.ok(DtoMapper.toCommandeDTO(updated)).build();
        } finally {
            em.close();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            CommandeService service = new CommandeService(em);

            Commande existing = service.findById(id);
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
    @Path("/client/{clientId}")
    public Response findByClient(@PathParam("clientId") Long clientId) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            CommandeService service = new CommandeService(em);

            List<CommandeDTO> result = service.commandesParClient(clientId)
                    .stream()
                    .map(DtoMapper::toCommandeDTO)
                    .collect(Collectors.toList());

            return Response.ok(result).build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/statut/{statut}")
    public Response findByStatut(@PathParam("statut") StatutCommande statut) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            CommandeService service = new CommandeService(em);

            List<CommandeDTO> result = service.commandesParStatut(statut)
                    .stream()
                    .map(DtoMapper::toCommandeDTO)
                    .collect(Collectors.toList());

            return Response.ok(result).build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/confirmees")
    public Response findCommandesConfirmees() {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            CommandeService service = new CommandeService(em);

            List<CommandeDTO> result = service.commandesConfirmees()
                    .stream()
                    .map(DtoMapper::toCommandeDTO)
                    .collect(Collectors.toList());

            return Response.ok(result).build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/client/{clientId}/montant-total")
    public Response montantTotalClient(@PathParam("clientId") Long clientId) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            CommandeService service = new CommandeService(em);
            BigDecimal total = service.montantTotalClient(clientId);

            return Response.ok(total).build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/client/{clientId}/count")
    public Response nombreCommandesClient(@PathParam("clientId") Long clientId) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            CommandeService service = new CommandeService(em);
            long count = service.nombreCommandesClient(clientId);

            return Response.ok(count).build();
        } finally {
            em.close();
        }
    }

    @PUT
    @Path("/{id}/annuler")
    public Response annulerCommande(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            CommandeService service = new CommandeService(em);
            Commande commande = service.annulerCommande(id);

            if (commande == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.ok(DtoMapper.toCommandeDTO(commande)).build();
        } finally {
            em.close();
        }
    }
}
