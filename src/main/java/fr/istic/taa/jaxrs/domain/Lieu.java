package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Lieu {

    private Long id;
    private String nom;
    private String adresse;
    private String ville;
    private String pays;
    private int capacite;

    private List<Evenement> evenements = new ArrayList<>();

    public Lieu() {
    }

    public Lieu(String nom, String adresse, String ville, String pays, int capacite) {
        this.nom = nom;
        this.adresse = adresse;
        this.ville = ville;
        this.pays = pays;
        this.capacite = capacite;
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
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Column(nullable = false)
    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    @OneToMany(mappedBy = "lieu", cascade = CascadeType.PERSIST)
    public List<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(List<Evenement> evenements) {
        this.evenements = evenements;
    }

    @Override
    public String toString() {
        return "Lieu{id=" + id + ", nom='" + nom + "', ville='" + ville + "'}";
    }


}
