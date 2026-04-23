package fr.istic.taa.jaxrs.domain;

import fr.istic.taa.jaxrs.domain.enumeration.StatutTransfert;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transfert {

    private Long id;
    private LocalDateTime dateTransfert;
    private StatutTransfert statut;

    private Ticket ticket;
    private Client expediteur;
    private Client destinataire;

    public Transfert() {
    }

    public Transfert(LocalDateTime dateTransfert, StatutTransfert statut) {
        this.dateTransfert = dateTransfert;
        this.statut = statut;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTransfert() {
        return dateTransfert;
    }

    public void setDateTransfert(LocalDateTime dateTransfert) {
        this.dateTransfert = dateTransfert;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public StatutTransfert getStatut() {
        return statut;
    }

    public void setStatut(StatutTransfert statut) {
        this.statut = statut;
    }

    @OneToOne
    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @ManyToOne
    public Client getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(Client expediteur) {
        this.expediteur = expediteur;
    }

    @ManyToOne
    public Client getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Client destinataire) {
        this.destinataire = destinataire;
    }

    @Override
    public String toString() {
        return "Transfert{id=" + id +
                ", dateTransfert=" + dateTransfert +
                ", statut=" + statut +
                '}';
    }
}
