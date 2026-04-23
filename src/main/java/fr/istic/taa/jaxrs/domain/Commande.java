package fr.istic.taa.jaxrs.domain;


import fr.istic.taa.jaxrs.domain.enumeration.StatutCommande;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Commande {

    private Long id;
    private LocalDateTime dateCommande;
    private BigDecimal montantTotal;
    private StatutCommande statut;

    private Client client;
    private List<Ticket> tickets = new ArrayList<>();

    public Commande() {
    }

    public Commande(LocalDateTime dateCommande, BigDecimal montantTotal, StatutCommande statut) {
        this.dateCommande = dateCommande;
        this.montantTotal = montantTotal;
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

    public LocalDateTime getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDateTime dateCommande) {
        this.dateCommande = dateCommande;
    }

    @Column(nullable = false)
    public BigDecimal getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(BigDecimal montantTotal) {
        this.montantTotal = montantTotal;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public StatutCommande getStatut() {
        return statut;
    }

    public void setStatut(StatutCommande statut) {
        this.statut = statut;
    }

    @ManyToOne
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @OneToMany(mappedBy = "commande", cascade = CascadeType.PERSIST)
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
        ticket.setCommande(this);
    }

    @Override
    public String toString() {
        return "Commande{id=" + id +
                ", dateCommande=" + dateCommande +
                ", montantTotal=" + montantTotal +
                ", statut=" + statut +
                '}';
    }
}
