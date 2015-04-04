package odesk.test.jpa;

import odesk.test.model.Doc;
import odesk.test.model.Version;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * User: goblin72
 * Date: 13.03.2015
 * Time: 11:47
 */
public class VersionDAO extends DAO {

    public void insertVersion(Version version) {
        EntityManager em = createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.persist(version);
        if (version.getDocs() != null) {
            for (Doc doc : version.getDocs()) {
                doc.setVersion(version);
                em.persist(doc);
            }
        }


        tx.commit();

        em.close();
    }

    public List<Version> getVersions() {
        EntityManager em = createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Query allVersionsQuery = em.createQuery("select v from Version v");
            return allVersionsQuery.getResultList();
        } finally {
            tx.commit();
            em.close();
        }
    }

}
