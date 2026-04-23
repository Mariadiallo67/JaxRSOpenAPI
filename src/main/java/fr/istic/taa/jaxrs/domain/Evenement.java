package fr.istic.taa.jaxrs.domain;


import fr.istic.taa.jaxrs.domain.enumeration.StatutEvenement;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(
        name = "Evenement.findByStatut",
        query = "SELECT e FROM Evenement e WHERE e.statut = :statut"
)
@Entity
public class Evenement {

    private Long id;
    private String titre;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private LocalDateTime datePublication;
    private StatutEvenement statut;
    private int capaciteTotale;

    private Organisateur organisateur;
    private Lieu lieu;
    private List<Artiste> artistes = new ArrayList<>();
    private List<CategorieTicket> categoriesTicket = new ArrayList<>();

    public Evenement() {
    }

    public Evenement(String titre, String description, LocalDateTime dateDebut,
                     LocalDateTime dateFin, LocalDateTime datePublication,
                     StatutEvenement statut, int capaciteTotale) {
        this.titre = titre;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.datePublication = datePublication;
        this.statut = statut;
        this.capaciteTotale = capaciteTotale;
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
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Column(length = 3000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public LocalDateTime getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDateTime datePublication) {
        this.datePublication = datePublication;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public StatutEvenement getStatut() {
        return statut;
    }

    public void setStatut(StatutEvenement statut) {
        this.statut = statut;
    }

    public int getCapaciteTotale() {
        return capaciteTotale;
    }

    public void setCapaciteTotale(int capaciteTotale) {
        this.capaciteTotale = capaciteTotale;
    }


    @ManyToOne
    public Organisateur getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(Organisateur organisateur) {
        this.organisateur = organisateur;
    }

    @ManyToOne
    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "evenement_artiste",
            joinColumns = @JoinColumn(name = "evenement_id"),
            inverseJoinColumns = @JoinColumn(name = "artiste_id")
    )
    public List<Artiste> getArtistes() {
        return artistes;
    }

    public void setArtistes(List<Artiste> artistes) {
        this.artistes = artistes;
    }

    public void addArtiste(Artiste artiste) {
        this.artistes.add(artiste);
        artiste.getEvenements().add(this);
    }

    @OneToMany(mappedBy = "evenement", cascade = CascadeType.PERSIST)
    public List<CategorieTicket> getCategoriesTicket() {
        return categoriesTicket;
    }

    public void setCategoriesTicket(List<CategorieTicket> categoriesTicket) {
        this.categoriesTicket = categoriesTicket;
    }

    public void addCategorieTicket(CategorieTicket categorieTicket) {
        this.categoriesTicket.add(categorieTicket);
        categorieTicket.setEvenement(this);
    }

    @Override
    public String toString() {
        return "Evenement{id=" + id +
                ", titre='" + titre + '\'' +
                ", statut=" + statut +
                ", capaciteTotale=" + capaciteTotale +
                '}';
    }
}
