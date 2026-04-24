package fr.istic.taa.jaxrs.dto;


import fr.istic.taa.jaxrs.domain.enumeration.StatutEvenement;

import java.time.LocalDateTime;
import java.util.List;

public class EvenementDTO {

    private Long id;
    private String titre;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private StatutEvenement statut;
    private int capaciteTotale;

    private String nomLieu;
    private String ville;
    private String nomOrganisation;

    private List<String> artistes;
    private List<String> categoriesTicket;

    public EvenementDTO() {
    }

    public EvenementDTO(Long id, String titre, String description, LocalDateTime dateDebut,
                        LocalDateTime dateFin, StatutEvenement statut, int capaciteTotale,
                        String nomLieu, String ville,
                        String nomOrganisation, List<String> artistes,
                        List<String> categoriesTicket) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
        this.capaciteTotale = capaciteTotale;
        this.nomLieu = nomLieu;
        this.ville = ville;
        this.nomOrganisation = nomOrganisation;
        this.artistes = artistes;
        this.categoriesTicket = categoriesTicket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

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

    public String getNomLieu() {
        return nomLieu;
    }

    public void setNomLieu(String nomLieu) {
        this.nomLieu = nomLieu;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getNomOrganisation() {
        return nomOrganisation;
    }

    public void setNomOrganisation(String nomOrganisation) {
        this.nomOrganisation = nomOrganisation;
    }

    public List<String> getArtistes() {
        return artistes;
    }

    public void setArtistes(List<String> artistes) {
        this.artistes = artistes;
    }

    public List<String> getCategoriesTicket() {
        return categoriesTicket;
    }

    public void setCategoriesTicket(List<String> categoriesTicket) {
        this.categoriesTicket = categoriesTicket;
    }
}