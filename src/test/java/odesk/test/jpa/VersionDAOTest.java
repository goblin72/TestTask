package odesk.test.jpa;

import odesk.test.model.Doc;
import odesk.test.model.Version;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class VersionDAOTest {

    private final String END_OF_LIFE = "end of life";
    private final String RELEASE_DATE = "release date";
    private final String CODE_NAME = "code name";
    private final String VERSION_1 = "version 1";
    private final String VERSION_2 = "version 2";

    private VersionDAO dao;

    @BeforeSuite
    public void setUp() throws Exception {
        System.out.println("VersionDAOTest.setUp");
        dao = new VersionDAO();
    }

    @AfterSuite
    public void tearDown() throws Exception {
        System.out.println("VersionDAOTest.tearDown");
        DAO.closeEntityManager();
    }

    @Test(groups = "insert")
    public void insertVersion() throws Exception {
        System.out.println("VersionDAOTest.insertVersion");
        dao.insertVersion(getVersion(VERSION_1, 0));
    }

    @Test(groups = "insert")
    public void insertVersionWithDocs() throws Exception {
        System.out.println("VersionDAOTest.insertVersionWithDocs");
        dao.insertVersion(getVersion(VERSION_2, 2));
    }

    @Test(groups = "select", dependsOnGroups = "insert")
    public void selectVersion() throws Exception {
        System.out.println("VersionDAOTest.selectVersion");
        List<Version> versions = dao.getVersions();
        assertNotNull(versions);
        assertEquals(versions.size(), 2);
        for (Version currentVersion : versions) {
            assertEquals(currentVersion.getEndOfLife(), END_OF_LIFE);
            assertEquals(currentVersion.getReleaseDate(), RELEASE_DATE);
            assertEquals(currentVersion.getCodeName(), CODE_NAME);
            assertNotNull(currentVersion.getDocs());
            if (currentVersion.getVersion().equals(VERSION_2)) {
                assertEquals(currentVersion.getDocs().size(), 2);
            } else {
                assertEquals(currentVersion.getDocs().size(), 0);
            }
        }
    }

    private Version getVersion(String version, int numberOfDocs) {
        Version result = new Version();
        result.setVersion(version);
        result.setEndOfLife(END_OF_LIFE);
        result.setReleaseDate(RELEASE_DATE);
        result.setCodeName(CODE_NAME);

        for (int i = 0; i < numberOfDocs; i++) {
            Doc doc = new Doc();
            doc.setDocUrl("doc url");
            doc.setDocType("type " + i);
            result.getDocs().add(doc);
        }
        return result;
    }

}
