package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.ClientDao;
import fr.istic.taa.jaxrs.domain.Client;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ClientService {

    private final EntityManager em;
    private final ClientDao clientDao;

    public ClientService(EntityManager em) {
        this.em = em;
        this.clientDao = new ClientDao(em);
    }

    public Client findById(Long id) {
        return clientDao.findById(id);
    }

    public List<Client> findAll() {
        return clientDao.findAll();
    }

    public Client create(Client client) {
        em.getTransaction().begin();
        clientDao.save(client);
        em.getTransaction().commit();
        return client;
    }

    public Client update(Client client) {
        em.getTransaction().begin();
        Client updated = clientDao.update(client);
        em.getTransaction().commit();
        return updated;
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        clientDao.delete(id);
        em.getTransaction().commit();
    }
}
