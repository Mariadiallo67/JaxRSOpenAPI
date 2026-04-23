package fr.istic.taa.jaxrs.domain;

import fr.istic.taa.jaxrs.domain.enumeration.StatutTicket;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Ticket {

    private Long id;
    private String codeQR;
    private LocalDateTime dateAchat;
    private StatutTicket statut;

    private CategorieTicket categorieTicket;
    private Commande commande;
    private Client proprietaire;

    private Transfert transfert;

    public Ticket() {
    }

    public Ticket(String codeQR, LocalDateTime dateAchat, StatutTicket statut) {
        this.codeQR = codeQR;
        this.dateAchat = dateAchat;
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

    @Column(nullable = false, unique = true)
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public StatutTicket getStatut() {
        return statut;
    }

    public void setStatut(StatutTicket statut) {
        this.statut = statut;
    }

    @ManyToOne
    public CategorieTicket getCategorieTicket() {
        return categorieTicket;
    }

    public void setCategorieTicket(CategorieTicket categorieTicket) {
        this.categorieTicket = categorieTicket;
    }

    @ManyToOne
    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    @ManyToOne
    public Client getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Client proprietaire) {
        this.proprietaire = proprietaire;
    }

    @OneToOne(mappedBy = "ticket", cascade = CascadeType.PERSIST)
    public Transfert getTransfert() {
        return transfert;
    }

    public void setTransfert(Transfert transfert) {
        this.transfert = transfert;
    }

    @Override
    public String toString() {
        return "Ticket{id=" + id +
                ", codeQR='" + codeQR + '\'' +
                ", statut=" + statut +
                '}';
    }
}
