package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.GenericDao;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.enumeration.StatutTicket;
import jakarta.persistence.EntityManager;

import java.util.List;

public class TicketDao extends GenericDao<Ticket> {

    public TicketDao(EntityManager em) {
        super(em, Ticket.class);
    }

    public List<Ticket> findByProprietaire(Long clientId) {
        return em.createQuery(
                "SELECT t FROM Ticket t WHERE t.proprietaire.id = :clientId",
                Ticket.class
        ).setParameter("clientId", clientId).getResultList();
    }

    public List<Ticket> findByCategorie(Long categorieId) {
        return em.createQuery(
                "SELECT t FROM Ticket t WHERE t.categorieTicket.id = :categorieId",
                Ticket.class
        ).setParameter("categorieId", categorieId).getResultList();
    }

    public Ticket findByCodeQR(String codeQR) {
        List<Ticket> result = em.createQuery(
                "SELECT t FROM Ticket t WHERE t.codeQR = :codeQR",
                Ticket.class
        ).setParameter("codeQR", codeQR).getResultList();

        return result.isEmpty() ? null : result.get(0);
    }


    public List<Ticket> findTicketsValidesByClient(Long clientId) {
        return em.createQuery(
                        "SELECT t FROM Ticket t WHERE t.proprietaire.id = :clientId AND t.statut = :statut",
                        Ticket.class
                ).setParameter("clientId", clientId)
                .setParameter("statut", StatutTicket.VALIDE)
                .getResultList();
    }


    public long compterTicketsVendusPourEvenement(Long evenementId) {
        return em.createQuery(
                "SELECT COUNT(t) FROM Ticket t WHERE t.categorieTicket.evenement.id = :evenementId",
                Long.class
        ).setParameter("evenementId", evenementId).getSingleResult();
    }


    public List<Ticket> findTicketsTransferes() {
        return em.createQuery(
                "SELECT t FROM Ticket t WHERE t.statut = :statut",
                Ticket.class
        ).setParameter("statut", StatutTicket.TRANSFERE).getResultList();
    }
}
