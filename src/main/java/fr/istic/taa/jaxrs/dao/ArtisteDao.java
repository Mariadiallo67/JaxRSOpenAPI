package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.GenericDao;
import fr.istic.taa.jaxrs.domain.Artiste;
import jakarta.persistence.EntityManager;

public class ArtisteDao extends GenericDao<Artiste> {

    public ArtisteDao(EntityManager em) {
        super(em, Artiste.class);
    }
}
