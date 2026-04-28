package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.Evenement;
import fr.istic.taa.jaxrs.domain.enumeration.StatutEvenement;
import fr.istic.taa.jaxrs.dto.EvenementDTO;
import fr.istic.taa.jaxrs.dto.mapper.DtoMapper;
import fr.istic.taa.jaxrs.service.EvenementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/evenements")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Evenements", description = "Gestion des événements musicaux")
public class EvenementResource {

    @GET
    @Operation(summary = "Lister tous les événements")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des événements retournée avec succès")
    })
    public Response findAll() {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            EvenementService service = new EvenementService(em);

            List<EvenementDTO> result = service.findAll()
                    .stream()
                    .map(DtoMapper::toEvenementDTO)
                    .collect(Collectors.toList());

            return Response.ok(result).build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Récupérer un événement par son identifiant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Événement trouvé"),
            @ApiResponse(responseCode = "404", description = "Événement introuvable")
    })
    public Response findById(@Parameter(description = "Identifiant de l'événement") @PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            EvenementService service = new EvenementService(em);
            Evenement evenement = service.findById(id);

            if (evenement == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.ok(DtoMapper.toEvenementDTO(evenement)).build();
        } finally {
            em.close();
        }
    }

    @POST
    @Operation(summary = "Créer un événement")
    public Response create(Evenement evenement) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            EvenementService service = new EvenementService(em);
            Evenement created = service.create(evenement);
            return Response.status(Response.Status.CREATED)
                    .entity(DtoMapper.toEvenementDTO(created))
                    .build();
        } finally {
            em.close();
        }
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Modifier un événement")
    public Response update(@PathParam("id") Long id, Evenement evenement) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            EvenementService service = new EvenementService(em);

            Evenement existing = service.findById(id);
            if (existing == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            evenement.setId(id);
            Evenement updated = service.update(evenement);

            return Response.ok(DtoMapper.toEvenementDTO(updated)).build();
        } finally {
            em.close();
        }
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Supprimer un événement")
    public Response delete(@PathParam("id") Long id) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            EvenementService service = new EvenementService(em);

            Evenement existing = service.findById(id);
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
    @Path("/ville/{ville}")
    @Operation(summary = "Rechercher les événements par ville")
    public Response findByVille(@PathParam("ville") String ville) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            EvenementService service = new EvenementService(em);

            List<EvenementDTO> result = service.rechercherParVille(ville)
                    .stream()
                    .map(DtoMapper::toEvenementDTO)
                    .collect(Collectors.toList());

            return Response.ok(result).build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/statut/{statut}")
    @Operation(summary = "Rechercher les événements par statut")
    public Response findByStatut(@PathParam("statut") StatutEvenement statut) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            EvenementService service = new EvenementService(em);

            List<EvenementDTO> result = service.rechercherParStatut(statut)
                    .stream()
                    .map(DtoMapper::toEvenementDTO)
                    .collect(Collectors.toList());

            return Response.ok(result).build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/avenir")
    @Operation(summary = "Lister les événements à venir")
    public Response findEvenementsAVenir() {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            EvenementService service = new EvenementService(em);

            List<EvenementDTO> result = service.evenementsAVenir()
                    .stream()
                    .map(DtoMapper::toEvenementDTO)
                    .collect(Collectors.toList());

            return Response.ok(result).build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/valides")
    @Operation(summary = "Lister les événements validés")
    public Response findEvenementsValides() {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            EvenementService service = new EvenementService(em);

            List<EvenementDTO> result = service.evenementsValides()
                    .stream()
                    .map(DtoMapper::toEvenementDTO)
                    .collect(Collectors.toList());

            return Response.ok(result).build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/organisateur/{organisateurId}")
    @Operation(summary = "Lister les événements d'un organisateur")
    public Response findByOrganisateur(
            @Parameter(description = "Identifiant de l'organisateur")
            @PathParam("organisateurId") Long organisateurId) {

        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            EvenementService service = new EvenementService(em);

            List<EvenementDTO> result = service.evenementsParOrganisateur(organisateurId)
                    .stream()
                    .map(DtoMapper::toEvenementDTO)
                    .collect(Collectors.toList());

            return Response.ok(result).build();
        } finally {
            em.close();
        }
    }

    @GET
    @Path("/capacite/{min}")
    @Operation(summary = "Rechercher les événements avec une capacité minimale")
    public Response findByCapaciteMin(@PathParam("min") int min) {
        EntityManager em = EntityManagerProvider.getEntityManager();
        try {
            EvenementService service = new EvenementService(em);

            List<EvenementDTO> result = service.rechercherParCapaciteMin(min)
                    .stream()
                    .map(DtoMapper::toEvenementDTO)
                    .collect(Collectors.toList());

            return Response.ok(result).build();
        } finally {
            em.close();
        }
    }
}
