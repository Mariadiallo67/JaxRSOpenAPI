package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.EvenementDao;
import fr.istic.taa.jaxrs.domain.Evenement;
import fr.istic.taa.jaxrs.domain.enumeration.StatutEvenement;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EvenementService {

    private final EntityManager em;
    private final EvenementDao evenementDao;

    public EvenementService(EntityManager em) {
        this.em = em;
        this.evenementDao = new EvenementDao(em);
    }

    public Evenement findById(Long id) {
        return evenementDao.findById(id);
    }

    public List<Evenement> findAll() {
        return evenementDao.findAll();
    }

    public Evenement create(Evenement evenement) {
        em.getTransaction().begin();
        evenementDao.save(evenement);
        em.getTransaction().commit();
        return evenement;
    }

    public Evenement update(Evenement evenement) {
        em.getTransaction().begin();
        Evenement updated = evenementDao.update(evenement);
        em.getTransaction().commit();
        return updated;
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        evenementDao.delete(id);
        em.getTransaction().commit();
    }

    public List<Evenement> rechercherParVille(String ville) {
        return evenementDao.findByVille(ville);
    }

    public List<Evenement> rechercherParStatut(StatutEvenement statut) {
        return evenementDao.findByStatut(statut);
    }

    public List<Evenement> rechercherParCapaciteMin(int capaciteMin) {
        return evenementDao.findByCapaciteMin(capaciteMin);
    }

    public List<Evenement> evenementsValides() {
        return evenementDao.findEvenementsValides();
    }

    public List<Evenement> evenementsAVenir() {
        return evenementDao.findEvenementsAVenir();
    }

    public List<Evenement> evenementsParOrganisateur(Long organisateurId) {
        return evenementDao.findByOrganisateur(organisateurId);
    }
}
