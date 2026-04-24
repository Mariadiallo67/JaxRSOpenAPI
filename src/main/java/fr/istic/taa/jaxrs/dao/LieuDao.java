package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.GenericDao;
import fr.istic.taa.jaxrs.domain.Lieu;
import jakarta.persistence.EntityManager;

public class LieuDao extends GenericDao<Lieu> {

    public LieuDao(EntityManager em) {
        super(em, Lieu.class);
    }
}
