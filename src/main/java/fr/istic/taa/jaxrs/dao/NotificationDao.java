package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.GenericDao;
import fr.istic.taa.jaxrs.domain.Notification;
import jakarta.persistence.EntityManager;

public class NotificationDao extends GenericDao<Notification> {

    public NotificationDao(EntityManager em) {
        super(em, Notification.class);
    }
}
