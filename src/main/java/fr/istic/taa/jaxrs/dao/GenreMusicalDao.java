package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.GenericDao;
import fr.istic.taa.jaxrs.domain.GenreMusical;
import jakarta.persistence.EntityManager;

public class GenreMusicalDao extends GenericDao<GenreMusical> {
    public GenreMusicalDao(EntityManager em) {
        super(em, GenreMusical.class);
    }
}
