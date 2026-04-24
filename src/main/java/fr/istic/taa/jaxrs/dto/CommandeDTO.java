package fr.istic.taa.jaxrs.dto;


import fr.istic.taa.jaxrs.domain.enumeration.StatutCommande;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CommandeDTO {

    private Long id;
    private LocalDateTime dateCommande;
    private BigDecimal montantTotal;
    private StatutCommande statut;
    private Long clientId;
    private String emailClient;
    private List<String> codesTickets;

    public CommandeDTO() {
    }

    public CommandeDTO(Long id, LocalDateTime dateCommande, BigDecimal montantTotal,
                       StatutCommande statut, Long clientId, String emailClient,
                       List<String> codesTickets) {
        this.id = id;
        this.dateCommande = dateCommande;
        this.montantTotal = montantTotal;
        this.statut = statut;
        this.clientId = clientId;
        this.emailClient = emailClient;
        this.codesTickets = codesTickets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDateTime dateCommande) {
        this.dateCommande = dateCommande;
    }

    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    public StatutCommande getStatut() {
        return statut;
    }

    public void setStatut(StatutCommande statut) {
        this.statut = statut;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public List<String> getCodesTickets() {
        return codesTickets;
    }

    public void setCodesTickets(List<String> codesTickets) {
        this.codesTickets = codesTickets;
    }
}
