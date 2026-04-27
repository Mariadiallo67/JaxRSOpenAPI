package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.ClientDao;
import fr.istic.taa.jaxrs.dao.TicketDao;
import fr.istic.taa.jaxrs.domain.Client;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.enumeration.StatutTicket;
import jakarta.persistence.EntityManager;

import java.util.List;

public class TicketService {

    private final EntityManager em;
    private final TicketDao ticketDao;
    private final ClientDao clientDao;

    public TicketService(EntityManager em) {
        this.em = em;
        this.ticketDao = new TicketDao(em);
        this.clientDao = new ClientDao(em);
    }

    public Ticket findById(Long id) {
        return ticketDao.findById(id);
    }

    public List<Ticket> findAll() {
        return ticketDao.findAll();
    }

    public Ticket create(Ticket ticket) {
        em.getTransaction().begin();
        ticketDao.save(ticket);
        em.getTransaction().commit();
        return ticket;
    }

    public Ticket update(Ticket ticket) {
        em.getTransaction().begin();
        Ticket updated = ticketDao.update(ticket);
        em.getTransaction().commit();
        return updated;
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        ticketDao.delete(id);
        em.getTransaction().commit();
    }

    public List<Ticket> ticketsParProprietaire(Long clientId) {
        return ticketDao.findByProprietaire(clientId);
    }

    public List<Ticket> ticketsValidesParClient(Long clientId) {
        return ticketDao.findTicketsValidesByClient(clientId);
    }

    public Ticket findByCodeQR(String codeQR) {
        return ticketDao.findByCodeQR(codeQR);
    }

    public List<Ticket> ticketsTransferes() {
        return ticketDao.findTicketsTransferes();
    }

    public long compterTicketsVendusPourEvenement(Long evenementId) {
        return ticketDao.compterTicketsVendusPourEvenement(evenementId);
    }

    public Ticket marquerCommeUtilise(Long ticketId) {
        em.getTransaction().begin();

        Ticket ticket = ticketDao.findById(ticketId);
        if (ticket != null) {
            ticket.setStatut(StatutTicket.UTILISE);
            ticket = ticketDao.update(ticket);
        }

        em.getTransaction().commit();
        return ticket;
    }

    public Ticket transfererTicket(Long ticketId, Long nouveauProprietaireId) {
        em.getTransaction().begin();

        Ticket ticket = ticketDao.findById(ticketId);
        Client nouveauProprietaire = clientDao.findById(nouveauProprietaireId);

        if (ticket != null && nouveauProprietaire != null) {
            ticket.setProprietaire(nouveauProprietaire);
            ticket.setStatut(StatutTicket.TRANSFERE);
            ticket = ticketDao.update(ticket);
        }

        em.getTransaction().commit();
        return ticket;
    }

    public List<Ticket> ticketsParCategorie(Long categorieId) {
        return ticketDao.findByCategorie(categorieId);
    }
}
