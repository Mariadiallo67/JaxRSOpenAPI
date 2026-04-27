package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.GenericDao;
import fr.istic.taa.jaxrs.domain.Client;
import jakarta.persistence.EntityManager;

public class ClientDao extends GenericDao<Client> {

    public ClientDao(EntityManager em) {
        super(em, Client.class);
    }

}
