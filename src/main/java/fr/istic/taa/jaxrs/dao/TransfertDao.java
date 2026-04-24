package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.GenericDao;
import fr.istic.taa.jaxrs.domain.Transfert;
import jakarta.persistence.EntityManager;

public class TransfertDao extends GenericDao<Transfert> {

    public TransfertDao(EntityManager em) {
        super(em, Transfert.class);
    }
}
