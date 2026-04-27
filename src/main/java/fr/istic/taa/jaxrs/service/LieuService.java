package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.LieuDao;
import fr.istic.taa.jaxrs.domain.Lieu;
import jakarta.persistence.EntityManager;

import java.util.List;

public class LieuService {

    private final EntityManager em;
    private final LieuDao lieuDao;

    public LieuService(EntityManager em) {
        this.em = em;
        this.lieuDao = new LieuDao(em);
    }

    public Lieu findById(Long id) {
        return lieuDao.findById(id);
    }

    public List<Lieu> findAll() {
        return lieuDao.findAll();
    }

    public Lieu create(Lieu lieu) {
        em.getTransaction().begin();
        lieuDao.save(lieu);
        em.getTransaction().commit();
        return lieu;
    }

    public Lieu update(Lieu lieu) {
        em.getTransaction().begin();
        Lieu updated = lieuDao.update(lieu);
        em.getTransaction().commit();
        return updated;
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        lieuDao.delete(id);
        em.getTransaction().commit();
    }
}
