package odesk.test.html;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class HtmlPageFromFileReaderImpl implements HtmlPageReader {

    private String filePath;
    InputStream is;

    public HtmlPageFromFileReaderImpl(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public InputStream getPage() throws IOException {

        close(); //just in case
        URL url = getClass().getResource(filePath);
        is = url.openStream();
        return is;
    }

    @Override
    public void close() throws IOException {
        if (is != null)
            is.close();
    }

    @Override
    public String getUrl() {
        return filePath;
    }
}
