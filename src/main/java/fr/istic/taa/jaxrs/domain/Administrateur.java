package fr.istic.taa.jaxrs.domain;


import fr.istic.taa.jaxrs.domain.enumeration.NiveauAdmin;
import fr.istic.taa.jaxrs.domain.enumeration.StatutCompte;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDate;

@Entity
public class Administrateur extends Utilisateur {

    private NiveauAdmin niveau;

    public Administrateur() {
    }

    public Administrateur(String nom, String prenom, String email, String motDePasse,
                          String telephone, LocalDate dateInscription, StatutCompte statutCompte,
                          NiveauAdmin niveau) {
        super(nom, prenom, email, motDePasse, telephone, dateInscription, statutCompte);
        this.niveau = niveau;
    }

    @Enumerated(EnumType.STRING)
    public NiveauAdmin getNiveau() {
        return niveau;
    }

    public void setNiveau(NiveauAdmin niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return "Administrateur{id=" + getId() +
                ", email='" + getEmail() + '\'' +
                ", niveau=" + niveau +
                '}';
    }
}
