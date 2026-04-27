package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.GenreMusicalDao;
import fr.istic.taa.jaxrs.domain.GenreMusical;
import jakarta.persistence.EntityManager;

import java.util.List;

public class GenreMusicalService {

    private final EntityManager em;
    private final GenreMusicalDao genreMusicalDao;

    public GenreMusicalService(EntityManager em) {
        this.em = em;
        this.genreMusicalDao = new GenreMusicalDao(em);
    }

    public GenreMusical findById(Long id) {
        return genreMusicalDao.findById(id);
    }

    public List<GenreMusical> findAll() {
        return genreMusicalDao.findAll();
    }

    public GenreMusical create(GenreMusical genre) {
        em.getTransaction().begin();
        genreMusicalDao.save(genre);
        em.getTransaction().commit();
        return genre;
    }

    public GenreMusical update(GenreMusical genre) {
        em.getTransaction().begin();
        GenreMusical updated = genreMusicalDao.update(genre);
        em.getTransaction().commit();
        return updated;
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        genreMusicalDao.delete(id);
        em.getTransaction().commit();
    }
}
