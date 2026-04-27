package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.NotificationDao;
import fr.istic.taa.jaxrs.domain.Notification;
import jakarta.persistence.EntityManager;

import java.util.List;

public class NotificationService {

    private final EntityManager em;
    private final NotificationDao notificationDao;

    public NotificationService(EntityManager em) {
        this.em = em;
        this.notificationDao = new NotificationDao(em);
    }

    public Notification findById(Long id) {
        return notificationDao.findById(id);
    }

    public List<Notification> findAll() {
        return notificationDao.findAll();
    }

    public Notification create(Notification notification) {
        em.getTransaction().begin();
        notificationDao.save(notification);
        em.getTransaction().commit();
        return notification;
    }

    public Notification update(Notification notification) {
        em.getTransaction().begin();
        Notification updated = notificationDao.update(notification);
        em.getTransaction().commit();
        return updated;
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        notificationDao.delete(id);
        em.getTransaction().commit();
    }

    public Notification marquerCommeLue(Long id) {
        em.getTransaction().begin();

        Notification notification = notificationDao.findById(id);
        if (notification != null) {
            notification.setLu(true);
            notification = notificationDao.update(notification);
        }

        em.getTransaction().commit();
        return notification;
    }
}
