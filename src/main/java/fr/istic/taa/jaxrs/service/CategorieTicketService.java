package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.CategorieTicketDao;
import fr.istic.taa.jaxrs.domain.CategorieTicket;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CategorieTicketService {

    private final EntityManager em;
    private final CategorieTicketDao categorieTicketDao;

    public CategorieTicketService(EntityManager em) {
        this.em = em;
        this.categorieTicketDao = new CategorieTicketDao(em);
    }

    public CategorieTicket findById(Long id) {
        return categorieTicketDao.findById(id);
    }

    public List<CategorieTicket> findAll() {
        return categorieTicketDao.findAll();
    }

    public CategorieTicket create(CategorieTicket categorieTicket) {
        em.getTransaction().begin();
        categorieTicketDao.save(categorieTicket);
        em.getTransaction().commit();
        return categorieTicket;
    }

    public CategorieTicket update(CategorieTicket categorieTicket) {
        em.getTransaction().begin();
        CategorieTicket updated = categorieTicketDao.update(categorieTicket);
        em.getTransaction().commit();
        return updated;
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        categorieTicketDao.delete(id);
        em.getTransaction().commit();
    }
}
