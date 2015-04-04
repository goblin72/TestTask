package odesk.test.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * User: goblin72
 * Date: 13.03.2015
 * Time: 11:46
 */
public abstract class DAO {

    private static EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("test");

    public EntityManager createEntityManager() {
        return emf.createEntityManager();
    }

    public static void closeEntityManager() {
        emf.close();
    }

}