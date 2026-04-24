package fr.istic.taa.jaxrs.dto.mapper;

import fr.istic.taa.jaxrs.domain.*;
import fr.istic.taa.jaxrs.dto.*;

import java.util.stream.Collectors;

public class DtoMapper {

    public static EvenementDTO toEvenementDTO(Evenement e) {
        return new EvenementDTO(
                e.getId(),
                e.getTitre(),
                e.getDescription(),
                e.getDateDebut(),
                e.getDateFin(),
                e.getStatut(),
                e.getCapaciteTotale(),
                e.getLieu() != null ? e.getLieu().getNom() : null,
                e.getLieu() != null ? e.getLieu().getVille() : null,
                e.getOrganisateur() != null ? e.getOrganisateur().getNomOrganisation() : null,
                e.getArtistes().stream().map(Artiste::getNom).collect(Collectors.toList()),
                e.getCategoriesTicket().stream().map(CategorieTicket::getLibelle).collect(Collectors.toList())
        );
    }

    public static ClientDTO toClientDTO(Client c) {
        return new ClientDTO(
                c.getId(),
                c.getNom(),
                c.getPrenom(),
                c.getEmail(),
                c.getTelephone(),
                c.getDateInscription(),
                c.getStatutCompte(),
                c.getAdresse()
        );
    }

    public static CommandeDTO toCommandeDTO(Commande c) {
        return new CommandeDTO(
                c.getId(),
                c.getDateCommande(),
                c.getMontantTotal(),
                c.getStatut(),
                c.getClient() != null ? c.getClient().getId() : null,
                c.getClient() != null ? c.getClient().getEmail() : null,
                c.getTickets().stream().map(Ticket::getCodeQR).collect(Collectors.toList())
        );
    }

    public static TicketDTO toTicketDTO(Ticket t) {
        return new TicketDTO(
                t.getId(),
                t.getCodeQR(),
                t.getDateAchat(),
                t.getStatut(),
                t.getCategorieTicket() != null ? t.getCategorieTicket().getLibelle() : null,
                t.getCommande() != null ? t.getCommande().getId() : null,
                t.getProprietaire() != null ? t.getProprietaire().getId() : null,
                t.getProprietaire() != null ? t.getProprietaire().getEmail() : null
        );
    }

    public static TransfertDTO toTransfertDTO(Transfert t) {
        return new TransfertDTO(
                t.getId(),
                t.getDateTransfert(),
                t.getStatut(),
                t.getTicket() != null ? t.getTicket().getId() : null,
                t.getTicket() != null ? t.getTicket().getCodeQR() : null,
                t.getExpediteur() != null ? t.getExpediteur().getId() : null,
                t.getDestinataire() != null ? t.getDestinataire().getId() : null
        );
    }
}
