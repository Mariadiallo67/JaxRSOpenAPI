package fr.istic.taa.jaxrs.dao;


import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.dao.generic.GenericDao;
import fr.istic.taa.jaxrs.domain.Evenement;
import fr.istic.taa.jaxrs.domain.enumeration.StatutEvenement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.time.LocalDateTime;
import java.util.List;

public class EvenementDao  extends GenericDao<Evenement> {

    public EvenementDao(EntityManager em) {
        super(em, Evenement.class);
    }

    // Requête JPQL  Trouver un evenement par ville
    public List<Evenement> findByVille(String ville) {
        return em.createQuery(
                        "SELECT e FROM Evenement e WHERE e.lieu.ville = :ville",
                        Evenement.class
                ).setParameter("ville", ville)
                .getResultList();
    }

    // Requête nommée  Un evenement par statut
    public List<Evenement> findByStatut(StatutEvenement statut) {
        return em.createNamedQuery("Evenement.findByStatut", Evenement.class)
                .setParameter("statut", statut)
                .getResultList();
    }

    // Criteria Query
    public List<Evenement> findByCapaciteMin(int capaciteMin) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Evenement> cq = cb.createQuery(Evenement.class);
        Root<Evenement> root = cq.from(Evenement.class);

        cq.select(root)
                .where(cb.greaterThanOrEqualTo(root.get("capaciteTotale"), capaciteMin));

        return em.createQuery(cq).getResultList();
    }

    // Méthode métier
    public List<Evenement> findEvenementsValides() {
        return em.createQuery(
                        "SELECT e FROM Evenement e WHERE e.statut = :statut",
                        Evenement.class
                ).setParameter("statut", StatutEvenement.VALIDE)
                .getResultList();
    }

    // Méthode métier
    public List<Evenement> findByOrganisateur(Long organisateurId) {
        return em.createQuery(
                        "SELECT e FROM Evenement e WHERE e.organisateur.id = :id",
                        Evenement.class
                ).setParameter("id", organisateurId)
                .getResultList();
    }

    // Méthode métier
    public List<Evenement> findEvenementsAVenir() {
        return em.createQuery(
                "SELECT e FROM Evenement e WHERE e.dateDebut > :now ORDER BY e.dateDebut ASC",
                Evenement.class
        ).setParameter("now", LocalDateTime.now()).getResultList();
    }
}
