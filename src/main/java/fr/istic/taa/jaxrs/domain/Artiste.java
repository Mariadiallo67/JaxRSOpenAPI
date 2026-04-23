package fr.istic.taa.jaxrs.domain;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Artiste {

    private Long id;
    private String nom;
    private String biographie;
    private String photoUrl;

    private GenreMusical genreMusical;
    private List<Evenement> evenements = new ArrayList<>();

    public Artiste() {
    }

    public Artiste(String nom, String biographie, String photoUrl, GenreMusical genreMusical) {
        this.nom = nom;
        this.biographie = biographie;
        this.photoUrl = photoUrl;
        this.genreMusical = genreMusical;
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

    @Column(length = 2000)
    public String getBiographie() {
        return biographie;
    }

    public void setBiographie(String biographie) {
        this.biographie = biographie;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @ManyToOne
    public GenreMusical getGenreMusical() {
        return genreMusical;
    }

    public void setGenreMusical(GenreMusical genreMusical) {
        this.genreMusical = genreMusical;
    }

    @ManyToMany(mappedBy = "artistes")
    public List<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(List<Evenement> evenements) {
        this.evenements = evenements;
    }

    @Override
    public String toString() {
        return "Artiste{id=" + id + ", nom='" + nom + "'}";
    }
}
