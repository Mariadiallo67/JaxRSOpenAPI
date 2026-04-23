package fr.istic.taa.jaxrs.domain;


import fr.istic.taa.jaxrs.domain.enumeration.TypeNotification;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Notification {

    private Long id;
    private String titre;
    private String message;
    private LocalDateTime dateEnvoi;
    private TypeNotification type;
    private boolean lu;

    private Utilisateur destinataire;

    public Notification() {
    }

    public Notification(String titre, String message, LocalDateTime dateEnvoi, TypeNotification type, boolean lu) {
        this.titre = titre;
        this.message = message;
        this.dateEnvoi = dateEnvoi;
        this.type = type;
        this.lu = lu;
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

    @Column(length = 2000, nullable = false)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(LocalDateTime dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public TypeNotification getType() {
        return type;
    }

    public void setType(TypeNotification type) {
        this.type = type;
    }

    public boolean isLu() {
        return lu;
    }

    public void setLu(boolean lu) {
        this.lu = lu;
    }

    @ManyToOne
    public Utilisateur getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Utilisateur destinataire) {
        this.destinataire = destinataire;
    }

    @Override
    public String toString() {
        return "Notification{id=" + id +
                ", titre='" + titre + '\'' +
                ", type=" + type +
                ", lu=" + lu +
                '}';
    }
}
