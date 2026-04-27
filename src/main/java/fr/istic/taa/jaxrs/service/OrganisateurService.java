package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.OrganisateurDao;
import fr.istic.taa.jaxrs.domain.Organisateur;
import jakarta.persistence.EntityManager;

import java.util.List;

public class OrganisateurService {

    private final EntityManager em;
    private final OrganisateurDao organisateurDao;

    public OrganisateurService(EntityManager em) {
        this.em = em;
        this.organisateurDao = new OrganisateurDao(em);
    }

    public Organisateur findById(Long id) {
        return organisateurDao.findById(id);
    }

    public List<Organisateur> findAll() {
        return organisateurDao.findAll();
    }

    public Organisateur create(Organisateur organisateur) {
        em.getTransaction().begin();
        organisateurDao.save(organisateur);
        em.getTransaction().commit();
        return organisateur;
    }

    public Organisateur update(Organisateur organisateur) {
        em.getTransaction().begin();
        Organisateur updated = organisateurDao.update(organisateur);
        em.getTransaction().commit();
        return updated;
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        organisateurDao.delete(id);
        em.getTransaction().commit();
    }
}
