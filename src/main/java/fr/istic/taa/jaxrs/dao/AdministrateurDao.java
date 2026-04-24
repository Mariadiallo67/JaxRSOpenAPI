package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.GenericDao;
import fr.istic.taa.jaxrs.domain.Administrateur;
import jakarta.persistence.EntityManager;

public class AdministrateurDao extends GenericDao<Administrateur> {

    public AdministrateurDao(EntityManager em) {
        super(em, Administrateur.class);
    }
}
