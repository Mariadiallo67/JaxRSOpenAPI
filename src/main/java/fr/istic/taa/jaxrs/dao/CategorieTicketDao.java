package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.GenericDao;
import fr.istic.taa.jaxrs.domain.CategorieTicket;
import jakarta.persistence.EntityManager;

public class CategorieTicketDao extends GenericDao<CategorieTicket> {

    public CategorieTicketDao(EntityManager em) {
        super(em, CategorieTicket.class);
    }
}
