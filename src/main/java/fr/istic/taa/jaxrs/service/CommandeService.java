package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.CommandeDao;
import fr.istic.taa.jaxrs.domain.Commande;
import fr.istic.taa.jaxrs.domain.enumeration.StatutCommande;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

public class CommandeService {

    private final EntityManager em;
    private final CommandeDao commandeDao;

    public CommandeService(EntityManager em) {
        this.em = em;
        this.commandeDao = new CommandeDao(em);
    }

    public Commande findById(Long id) {
        return commandeDao.findById(id);
    }

    public List<Commande> findAll() {
        return commandeDao.findAll();
    }

    public Commande create(Commande commande) {
        em.getTransaction().begin();
        commandeDao.save(commande);
        em.getTransaction().commit();
        return commande;
    }

    public Commande update(Commande commande) {
        em.getTransaction().begin();
        Commande updated = commandeDao.update(commande);
        em.getTransaction().commit();
        return updated;
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        commandeDao.delete(id);
        em.getTransaction().commit();
    }

    public List<Commande> commandesParClient(Long clientId) {
        return commandeDao.findByClient(clientId);
    }

    public List<Commande> commandesParStatut(StatutCommande statut) {
        return commandeDao.findByStatut(statut);
    }

    public List<Commande> commandesConfirmees() {
        return commandeDao.findCommandesConfirmees();
    }

    public BigDecimal montantTotalClient(Long clientId) {
        return commandeDao.calculerMontantTotalClient(clientId);
    }

    public long nombreCommandesClient(Long clientId) {
        return commandeDao.compterCommandesClient(clientId);
    }

    public Commande annulerCommande(Long commandeId) {
        em.getTransaction().begin();

        Commande commande = commandeDao.findById(commandeId);
        if (commande != null) {
            commande.setStatut(StatutCommande.ANNULEE);
            commande = commandeDao.update(commande);
        }

        em.getTransaction().commit();
        return commande;
    }
}
