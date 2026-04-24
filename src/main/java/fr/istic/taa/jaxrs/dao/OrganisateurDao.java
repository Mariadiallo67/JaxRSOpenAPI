package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.GenericDao;
import fr.istic.taa.jaxrs.domain.Organisateur;
import jakarta.persistence.EntityManager;

public class OrganisateurDao extends GenericDao<Organisateur> {

    public OrganisateurDao(EntityManager em) {
        super(em, Organisateur.class);
    }
}
