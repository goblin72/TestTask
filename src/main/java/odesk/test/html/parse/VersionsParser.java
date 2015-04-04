package odesk.test.html.parse;

import odesk.test.html.HtmlPageReader;
import odesk.test.model.Doc;
import odesk.test.model.Version;
import org.hsqldb.lib.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class VersionsParser {

    private HtmlPageReader pageReader;

    public VersionsParser(HtmlPageReader pageReader) {
        this.pageReader = pageReader;
    }

    public List<Version> getVersions() throws IOException {
        List<Version> result = new ArrayList<Version>();
        try {
            InputStream is = pageReader.getPage();
            Document doc = Jsoup.parse(is, "utf-8", pageReader.getUrl());
            Elements versionRecords = doc.select("div[id=Releases.content] > div:eq(17) > table > tbody > tr:gt(1)");
            //here we can add a validation if the page structure has changed.
            // sort of if (versionRecords.size()==0) {throw some exception for this case}

            for (Element record : versionRecords) {
                Version version = new Version();
                Elements versionAttributes = record.select("td");
                version.setVersion(getTdContent(versionAttributes.get(0)));
                version.setCodeName(getTdContent(versionAttributes.get(1)));
                version.setDocs(getDocsFromTd(versionAttributes.get(2)));
                version.setReleaseDate(getTdContent(versionAttributes.get(3)));
                version.setEndOfLife(getTdContent(versionAttributes.get(4)));
                System.out.println(version);
                result.add(version);
            }
        } finally {
            pageReader.close();
        }
        return result;
    }

    private String getTdContent(Element p) {

        Elements elements = p.select("p > a");
        if (elements.size() == 1)
            return StringUtil.isEmpty(elements.get(0).text()) ? null : elements.get(0).text();
        elements = p.select("p");
        if (elements.size() == 1)
            return StringUtil.isEmpty(elements.get(0).text()) ? null : elements.get(0).text();
        return null;
    }

    private List<Doc> getDocsFromTd(Element p) {
        List<Doc> result = new ArrayList<Doc>();
        Elements elements = p.select("p > a");
        if (elements.size() > 0) {
            for (Element element : elements) {
                Doc doc = new Doc();
                doc.setDocType(element.text());

                doc.setDocUrl(element.attr("href"));
                result.add(doc);
            }
        }
        return result;
    }

}
