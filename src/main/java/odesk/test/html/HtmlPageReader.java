package odesk.test.html;

import java.io.IOException;
import java.io.InputStream;

/**
 * User: goblin72
 * Date: 18.03.2015
 * Time: 10:59
 */
public interface HtmlPageReader {

    public InputStream getPage() throws IOException;

    public void close() throws IOException;

    public String getUrl();
}
