package fr.istic.taa.jaxrs.domain;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class GenreMusical {

    private Long id;
    private String libelle;
    private List<Artiste> artistes = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();

    public GenreMusical() {
    }

    public GenreMusical(String libelle) {
        this.libelle = libelle;
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
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @OneToMany(mappedBy = "genreMusical", cascade = CascadeType.PERSIST)
    public List<Artiste> getArtistes() {
        return artistes;
    }

    public void setArtistes(List<Artiste> artistes) {
        this.artistes = artistes;
    }

    @ManyToMany(mappedBy = "preferences")
    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Genre{id=" + id + ", libelle='" + libelle + "'}";
    }
}
