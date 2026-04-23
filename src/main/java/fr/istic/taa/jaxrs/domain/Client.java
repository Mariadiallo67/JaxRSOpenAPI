package fr.istic.taa.jaxrs.domain;


import fr.istic.taa.jaxrs.domain.enumeration.StatutCompte;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client extends Utilisateur {

    private String adresse;
    private List<Commande> historiqueAchats = new ArrayList<>();
    private List<GenreMusical> preferences = new ArrayList<>();

    public Client() {
    }

    public Client(String nom, String prenom, String email, String motDePasse,
                  String telephone, LocalDate dateInscription, StatutCompte statutCompte,
                  String adresse) {
        super(nom, prenom, email, motDePasse, telephone, dateInscription, statutCompte);
        this.adresse = adresse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
    public List<Commande> getHistoriqueAchats() {
        return historiqueAchats;
    }

    public void setHistoriqueAchats(List<Commande> historiqueAchats) {
        this.historiqueAchats = historiqueAchats;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "client_genre_preference",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    public List<GenreMusical> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<GenreMusical> preferences) {
        this.preferences = preferences;
    }

    public void addCommande(Commande commande) {
        this.historiqueAchats.add(commande);
        commande.setClient(this);
    }

    public void addPreference(GenreMusical genre) {
        this.preferences.add(genre);
    }

    @Override
    public String toString() {
        return "Client{id=" + getId() + ", email='" + getEmail() + "', adresse='" + adresse + "'}";
    }
}
