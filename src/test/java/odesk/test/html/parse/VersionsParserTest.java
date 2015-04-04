package odesk.test.html.parse;

import odesk.test.html.HtmlPageFromFileReaderImpl;
import odesk.test.model.Doc;
import odesk.test.model.Version;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.*;

public class VersionsParserTest {

    @Test
    public void testIt() throws IOException {
        VersionsParser parser = new VersionsParser(new HtmlPageFromFileReaderImpl("/test_page.html"));
        List<Version> versions = parser.getVersions();
        assertNotNull(versions);
        assertEquals(versions.size(), 2);

        //check first version
        assertEquals(versions.get(0).getVersion(), "Ubuntu 14.10");
        assertEquals(versions.get(0).getReleaseDate(), "October 23, 2014");
        assertEquals(versions.get(0).getEndOfLife(), "July 2015");
        assertEquals(versions.get(0).getCodeName(), "Utopic Unicorn");
        assertNotNull(versions.get(0).getDocs());
        assertEquals(versions.get(0).getDocs().size(), 1);
        Iterator<Doc> iter = versions.get(0).getDocs().iterator();
        Doc doc = iter.next();

        assertEquals(doc.getDocType(), "Rel");
        assertEquals(doc.getDocUrl(), "/UtopicUnicorn/ReleaseNotes");

        //and the second one
        assertEquals(versions.get(1).getVersion(), "Ubuntu 10.04 LTS");
        assertEquals(versions.get(1).getReleaseDate(), "April 29, 2010");
        assertNull(versions.get(1).getEndOfLife());
        assertEquals(versions.get(1).getCodeName(), "Lucid Lynx");
        assertNotNull(versions.get(1).getDocs());
        assertEquals(versions.get(1).getDocs().size(), 2);
        iter = versions.get(1).getDocs().iterator();
        doc = iter.next();
        assertEquals(doc.getDocType(), "Tech");
        assertEquals(doc.getDocUrl(), "/LucidLynx/TechnicalOverview");
        doc = iter.next();
        assertEquals(doc.getDocType(), "Rel");
        assertEquals(doc.getDocUrl(), "/LucidLynx/ReleaseNotes");
    }


}
