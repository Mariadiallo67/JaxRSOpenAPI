package fr.istic.taa.jaxrs.rest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerProvider {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("dev");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
