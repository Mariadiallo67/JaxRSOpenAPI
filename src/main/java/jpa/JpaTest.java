package jpa;

import fr.istic.taa.jaxrs.domain.*;

import fr.istic.taa.jaxrs.domain.enumeration.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class JpaTest {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
        EntityManager manager = factory.createEntityManager();

        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        // Exemple de notification envoyer à client et transfert d'un ticket d'un client à un autre

        GenreMusical rock = new GenreMusical("Rock");
        GenreMusical pop = new GenreMusical("Pop");

        manager.persist(rock);
        manager.persist(pop);

        Artiste coldplay = new Artiste(
                "Coldplay",
                "Groupe pop rock britannique",
                "coldplay.jpg",
                rock
        );

        Artiste duaLipa = new Artiste(
                "Dua Lipa",
                "Chanteuse pop britannique",
                "dualipa.jpg",
                pop
        );

        Lieu accorArena = new Lieu(
                "Accor Arena",
                "8 Boulevard de Bercy",
                "Paris",
                "France",
                20000
        );

        manager.persist(accorArena);

        Organisateur organisateur = new Organisateur(
                "Martin",
                "Lucas",
                "orga@test.com",
                "secret",
                "0600000002",
                LocalDate.now(),
                StatutCompte.ACTIF,
                "Live Events France",
                "12345678900011",
                "https://live-events.fr",
                StatutOrganisateur.VALIDE
        );

        Evenement evenement = new Evenement(
                "Festival Pop & Rock",
                "Un grand événement musical avec plusieurs artistes.",
                LocalDateTime.of(2026, 6, 10, 18, 0),
                LocalDateTime.of(2026, 6, 10, 23, 30),
                LocalDateTime.now(),
                StatutEvenement.VALIDE,
                18000
        );

        evenement.setLieu(accorArena);
        evenement.addArtiste(coldplay);
        evenement.addArtiste(duaLipa);
        CategorieTicket fosse = new CategorieTicket(
                "Fosse",
                new BigDecimal("59.99"),
                10000,
                9998
        );

        CategorieTicket carreOr = new CategorieTicket(
                "Carré Or",
                new BigDecimal("129.99"),
                2000,
                1999
        );

        evenement.addCategorieTicket(fosse);
        evenement.addCategorieTicket(carreOr);
        organisateur.addEvenement(evenement);

        Client client1 = new Client(
                "Diallo",
                "Aminata",
                "aminata@test.com",
                "secret",
                "0600000001",
                LocalDate.now(),
                StatutCompte.ACTIF,
                "12 rue de Paris"
        );

        Client client2 = new Client(
                "Sow",
                "Ibrahima",
                "ibrahima@test.com",
                "secret",
                "0600000004",
                LocalDate.now(),
                StatutCompte.ACTIF,
                "8 avenue de Lyon"
        );

        client1.addPreference(rock);

        Notification notification = new Notification(
                "Confirmation d'achat",
                "Votre achat a bien été confirmé.",
                LocalDateTime.now(),
                TypeNotification.OFFRE,
                false
        );
        client1.addNotification(notification);

        Commande commande = new Commande(
                LocalDateTime.now(),
                new BigDecimal("189.98"),
                StatutCommande.CONFIRMEE
        );

        Ticket ticket1 = new Ticket(
                "QR-2026-0001",
                LocalDateTime.now(),
                StatutTicket.TRANSFERE
        );

        Ticket ticket2 = new Ticket(
                "QR-2026-0002",
                LocalDateTime.now(),
                StatutTicket.VALIDE
        );

        fosse.addTicket(ticket1);
        carreOr.addTicket(ticket2);

        commande.addTicket(ticket1);
        commande.addTicket(ticket2);

        ticket1.setProprietaire(client2);
        ticket2.setProprietaire(client1);

        client1.addCommande(commande);

        Transfert transfert = new Transfert(
                LocalDateTime.now(),
                StatutTransfert.ACCEPTE
        );
        transfert.setTicket(ticket1);
        transfert.setExpediteur(client1);
        transfert.setDestinataire(client2);
        ticket1.setTransfert(transfert);

        manager.persist(organisateur);
        manager.persist(client1);
        manager.persist(client2);

        tx.commit();

        List<Notification> notifications = manager
                .createQuery("SELECT n FROM Notification n", Notification.class)
                .getResultList();

        System.out.println("Nombre de notifications : " + notifications.size());
        for (Notification n : notifications) {
            System.out.println(n + " / destinataire=" +
                    (n.getDestinataire() != null ? n.getDestinataire().getEmail() : "aucun"));
        }

        List<Transfert> transferts = manager
                .createQuery("SELECT t FROM Transfert t", Transfert.class)
                .getResultList();

        System.out.println("Nombre de transferts : " + transferts.size());
        for (Transfert t : transferts) {
            System.out.println(t +
                    " / ticket=" + (t.getTicket() != null ? t.getTicket().getCodeQR() : "aucun") +
                    " / expediteur=" + (t.getExpediteur() != null ? t.getExpediteur().getEmail() : "aucun") +
                    " / destinataire=" + (t.getDestinataire() != null ? t.getDestinataire().getEmail() : "aucun"));
        }

        manager.close();
        factory.close();
    }
}
