package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CategorieTicket {

    private Long id;
    private String libelle;
    private BigDecimal prix;
    private int quantiteTotale;
    private int quantiteDisponible;

    private Evenement evenement;
    private List<Ticket> tickets = new ArrayList<>();

    public CategorieTicket() {
    }

    public CategorieTicket(String libelle, BigDecimal prix, int quantiteTotale, int quantiteDisponible) {
        this.libelle = libelle;
        this.prix = prix;
        this.quantiteTotale = quantiteTotale;
        this.quantiteDisponible = quantiteDisponible;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Column(nullable = false)
    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public int getQuantiteTotale() {
        return quantiteTotale;
    }

    public void setQuantiteTotale(int quantiteTotale) {
        this.quantiteTotale = quantiteTotale;
    }

    public int getQuantiteDisponible() {
        return quantiteDisponible;
    }

    public void setQuantiteDisponible(int quantiteDisponible) {
        this.quantiteDisponible = quantiteDisponible;
    }

    @ManyToOne
    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    @OneToMany(mappedBy = "categorieTicket", cascade = CascadeType.PERSIST)
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
        ticket.setCategorieTicket(this);
    }

    @Override
    public String toString() {
        return "CategorieTicket{id=" + id +
                ", libelle='" + libelle + '\'' +
                ", prix=" + prix +
                ", quantiteDisponible=" + quantiteDisponible +
                '}';
    }
}
