package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.ArtisteDao;
import fr.istic.taa.jaxrs.domain.Artiste;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ArtisteService {

    private final EntityManager em;
    private final ArtisteDao artisteDao;

    public ArtisteService(EntityManager em) {
        this.em = em;
        this.artisteDao = new ArtisteDao(em);
    }

    public Artiste findById(Long id) {
        return artisteDao.findById(id);
    }

    public List<Artiste> findAll() {
        return artisteDao.findAll();
    }

    public Artiste create(Artiste artiste) {
        em.getTransaction().begin();
        artisteDao.save(artiste);
        em.getTransaction().commit();
        return artiste;
    }

    public Artiste update(Artiste artiste) {
        em.getTransaction().begin();
        Artiste updated = artisteDao.update(artiste);
        em.getTransaction().commit();
        return updated;
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        artisteDao.delete(id);
        em.getTransaction().commit();
    }
}