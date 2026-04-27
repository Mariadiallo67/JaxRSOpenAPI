package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.TransfertDao;
import fr.istic.taa.jaxrs.domain.Transfert;
import fr.istic.taa.jaxrs.domain.enumeration.StatutTransfert;
import jakarta.persistence.EntityManager;

import java.util.List;

public class TransfertService {

    private final EntityManager em;
    private final TransfertDao transfertDao;

    public TransfertService(EntityManager em) {
        this.em = em;
        this.transfertDao = new TransfertDao(em);
    }

    public Transfert findById(Long id) {
        return transfertDao.findById(id);
    }

    public List<Transfert> findAll() {
        return transfertDao.findAll();
    }

    public Transfert create(Transfert transfert) {
        em.getTransaction().begin();
        transfertDao.save(transfert);
        em.getTransaction().commit();
        return transfert;
    }

    public Transfert update(Transfert transfert) {
        em.getTransaction().begin();
        Transfert updated = transfertDao.update(transfert);
        em.getTransaction().commit();
        return updated;
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        transfertDao.delete(id);
        em.getTransaction().commit();
    }

    public Transfert accepter(Long id) {
        em.getTransaction().begin();

        Transfert transfert = transfertDao.findById(id);
        if (transfert != null) {
            transfert.setStatut(StatutTransfert.ACCEPTE);
            transfert = transfertDao.update(transfert);
        }

        em.getTransaction().commit();
        return transfert;
    }

    public Transfert refuser(Long id) {
        em.getTransaction().begin();

        Transfert transfert = transfertDao.findById(id);
        if (transfert != null) {
            transfert.setStatut(StatutTransfert.REFUSE);
            transfert = transfertDao.update(transfert);
        }

        em.getTransaction().commit();
        return transfert;
    }
}
