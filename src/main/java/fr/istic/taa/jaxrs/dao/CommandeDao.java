package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.GenericDao;
import fr.istic.taa.jaxrs.domain.Commande;
import fr.istic.taa.jaxrs.domain.enumeration.StatutCommande;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

public class CommandeDao extends GenericDao<Commande> {

    public CommandeDao(EntityManager em) {
        super(em, Commande.class);
    }

    public List<Commande> findByClient(Long clientId) {
        return em.createQuery(
                "SELECT c FROM Commande c WHERE c.client.id = :clientId",
                Commande.class
        ).setParameter("clientId", clientId).getResultList();
    }

    public List<Commande> findByStatut(StatutCommande statut) {
        return em.createQuery(
                "SELECT c FROM Commande c WHERE c.statut = :statut",
                Commande.class
        ).setParameter("statut", statut).getResultList();
    }


    public List<Commande> findCommandesConfirmees() {

        return findByStatut(StatutCommande.CONFIRMEE);
    }

    public BigDecimal calculerMontantTotalClient(Long clientId) {
        BigDecimal total = em.createQuery(
                "SELECT COALESCE(SUM(c.montantTotal), 0) FROM Commande c WHERE c.client.id = :clientId",
                BigDecimal.class
        ).setParameter("clientId", clientId).getSingleResult();

        return total;
    }

    public long compterCommandesClient(Long clientId) {
        return em.createQuery(
                "SELECT COUNT(c) FROM Commande c WHERE c.client.id = :clientId",
                Long.class
        ).setParameter("clientId", clientId).getSingleResult();
    }
}