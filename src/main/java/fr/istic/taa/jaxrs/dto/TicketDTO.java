package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.enumeration.StatutTicket;

import java.time.LocalDateTime;

public class TicketDTO {

    private Long id;
    private String codeQR;
    private LocalDateTime dateAchat;
    private StatutTicket statut;
    private String categorie;
    private Long commandeId;
    private Long proprietaireId;
    private String emailProprietaire;

    public TicketDTO() {
    }

    public TicketDTO(Long id, String codeQR, LocalDateTime dateAchat,
                     StatutTicket statut, String categorie, Long commandeId,
                     Long proprietaireId, String emailProprietaire) {
        this.id = id;
        this.codeQR = codeQR;
        this.dateAchat = dateAchat;
        this.statut = statut;
        this.categorie = categorie;
        this.commandeId = commandeId;
        this.proprietaireId = proprietaireId;
        this.emailProprietaire = emailProprietaire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeQR() {
        return codeQR;
    }

    public void setCodeQR(String codeQR) {
        this.codeQR = codeQR;
    }

    public LocalDateTime getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(LocalDateTime dateAchat) {
        this.dateAchat = dateAchat;
    }

    public StatutTicket getStatut() {
        return statut;
    }

    public void setStatut(StatutTicket statut) {
        this.statut = statut;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Long getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Long commandeId) {
        this.commandeId = commandeId;
    }

    public Long getProprietaireId() {
        return proprietaireId;
    }

    public void setProprietaireId(Long proprietaireId) {
        this.proprietaireId = proprietaireId;
    }

    public String getEmailProprietaire() {
        return emailProprietaire;
    }

    public void setEmailProprietaire(String emailProprietaire) {
        this.emailProprietaire = emailProprietaire;
    }
}
