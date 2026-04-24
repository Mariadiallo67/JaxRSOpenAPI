package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.enumeration.StatutTransfert;

import java.time.LocalDateTime;

public class TransfertDTO {

    private Long id;
    private LocalDateTime dateTransfert;
    private StatutTransfert statut;
    private Long ticketId;
    private String codeQR;
    private Long expediteurId;
    private Long destinataireId;

    public TransfertDTO() {
    }

    public TransfertDTO(Long id, LocalDateTime dateTransfert, StatutTransfert statut,
                        Long ticketId, String codeQR, Long expediteurId,
                        Long destinataireId) {
        this.id = id;
        this.dateTransfert = dateTransfert;
        this.statut = statut;
        this.ticketId = ticketId;
        this.codeQR = codeQR;
        this.expediteurId = expediteurId;
        this.destinataireId = destinataireId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDestinataireId() {
        return destinataireId;
    }

    public void setDestinataireId(Long destinataireId) {
        this.destinataireId = destinataireId;
    }

    public Long getExpediteurId() {
        return expediteurId;
    }

    public void setExpediteurId(Long expediteurId) {
        this.expediteurId = expediteurId;
    }

    public String getCodeQR() {
        return codeQR;
    }

    public void setCodeQR(String codeQR) {
        this.codeQR = codeQR;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public StatutTransfert getStatut() {
        return statut;
    }

    public void setStatut(StatutTransfert statut) {
        this.statut = statut;
    }

    public LocalDateTime getDateTransfert() {
        return dateTransfert;
    }

    public void setDateTransfert(LocalDateTime dateTransfert) {
        this.dateTransfert = dateTransfert;
    }
}