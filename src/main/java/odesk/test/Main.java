package odesk.test;

import odesk.test.html.HtmlPageReader;
import odesk.test.html.HtmlPageReaderImpl;
import odesk.test.html.parse.VersionsParser;
import odesk.test.jpa.DAO;
import odesk.test.jpa.VersionDAO;
import odesk.test.model.Version;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        HtmlPageReader htmlPageReader = new HtmlPageReaderImpl("https://wiki.ubuntu.com/ReleaseTeam");
        VersionsParser parser = new VersionsParser(htmlPageReader);

        List<Version> versions = parser.getVersions();

        VersionDAO dao = new VersionDAO();
        for (Version version : versions) {
            dao.insertVersion(version);
        }

        System.out.println();
        System.out.println("load versions from DB and print them to the console (they are ordered by \"version name asc\" by default):");
        System.out.println();
        List<Version> loadedFromDbVersions = dao.getVersions();
        for (Version version : loadedFromDbVersions) {
            System.out.println(version);
        }

        DAO.closeEntityManager();
    }
}
