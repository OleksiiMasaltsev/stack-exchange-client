package util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Root;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;

public class ApiHttpClient {
    private final HttpClient httpClient = HttpClient.newHttpClient();

    private final ObjectMapper objectMapper = new ObjectMapper();

    {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public Root get(String url) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept-Encoding", "gzip")
                .GET()
                .build();
        HttpResponse<InputStream> httpResponse;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofInputStream());
            return objectMapper.readValue(unzip(httpResponse.body()), Root.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't get a value from url", e);
        }
    }

    private String unzip(InputStream is) throws IOException {
        InputStream bodyStream = new GZIPInputStream(is);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int length;
        while ((length = bodyStream.read(buffer)) > 0) {
            outStream.write(buffer, 0, length);
        }

        return outStream.toString("UTF-8");
    }
}
