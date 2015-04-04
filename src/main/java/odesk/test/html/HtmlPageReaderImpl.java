package odesk.test.html;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;

public class HtmlPageReaderImpl implements HtmlPageReader {

    private String url;
    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpGet httpGet;
    CloseableHttpResponse response;

    public HtmlPageReaderImpl(String url) {
        this.url = url;
        httpGet = new HttpGet(url);
    }

    public InputStream getPage() throws IOException {
        close(); // just in case
        response = httpclient.execute(httpGet);
        return response.getEntity().getContent();
    }

    public void close() throws IOException {
        if (response != null)
            response.close();
    }

    public String getUrl() {
        return url;
    }
}
