package fr.istic.taa.jaxrs.domain;


import fr.istic.taa.jaxrs.domain.enumeration.StatutCompte;
import fr.istic.taa.jaxrs.domain.enumeration.StatutOrganisateur;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Organisateur extends Utilisateur {

    private String nomOrganisation;
    private String siret;
    private String siteWeb;
    private StatutOrganisateur statutOrganisateur;

    private List<Evenement> evenements = new ArrayList<>();

    public Organisateur() {
    }

    public Organisateur(String nom, String prenom, String email, String motDePasse,
                        String telephone, LocalDate dateInscription, StatutCompte statutCompte,
                        String nomOrganisation, String siret, String siteWeb,
                        StatutOrganisateur statutOrganisateur) {
        super(nom, prenom, email, motDePasse, telephone, dateInscription, statutCompte);
        this.nomOrganisation = nomOrganisation;
        this.siret = siret;
        this.siteWeb = siteWeb;
        this.statutOrganisateur = statutOrganisateur;
    }

    public String getNomOrganisation() {
        return nomOrganisation;
    }

    public void setNomOrganisation(String nomOrganisation) {
        this.nomOrganisation = nomOrganisation;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    @Enumerated(EnumType.STRING)
    public StatutOrganisateur getStatutOrganisateur() {
        return statutOrganisateur;
    }

    public void setStatutOrganisateur(StatutOrganisateur statutOrganisateur) {
        this.statutOrganisateur = statutOrganisateur;
    }

    @OneToMany(mappedBy = "organisateur", cascade = CascadeType.PERSIST)
    public List<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(List<Evenement> evenements) {
        this.evenements = evenements;
    }

    public void addEvenement(Evenement evenement) {
        this.evenements.add(evenement);
        evenement.setOrganisateur(this);
    }

    @Override
    public String toString() {
        return "Organisateur{id=" + getId() +
                ", nomOrganisation='" + nomOrganisation + '\'' +
                ", email='" + getEmail() + '\'' +
                ", statutOrganisateur=" + statutOrganisateur +
                '}';
    }
}
