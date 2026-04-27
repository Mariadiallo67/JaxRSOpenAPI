package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.AdministrateurDao;
import fr.istic.taa.jaxrs.domain.Administrateur;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AdministrateurService {

    private final EntityManager em;
    private final AdministrateurDao administrateurDao;

    public AdministrateurService(EntityManager em) {
        this.em = em;
        this.administrateurDao = new AdministrateurDao(em);
    }

    public Administrateur findById(Long id) {
        return administrateurDao.findById(id);
    }

    public List<Administrateur> findAll() {
        return administrateurDao.findAll();
    }

    public Administrateur create(Administrateur administrateur) {
        em.getTransaction().begin();
        administrateurDao.save(administrateur);
        em.getTransaction().commit();
        return administrateur;
    }

    public Administrateur update(Administrateur administrateur) {
        em.getTransaction().begin();
        Administrateur updated = administrateurDao.update(administrateur);
        em.getTransaction().commit();
        return updated;
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        administrateurDao.delete(id);
        em.getTransaction().commit();
    }
}
