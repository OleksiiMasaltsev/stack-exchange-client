package util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiHttpClient {
    private final HttpClient httpClient = HttpClient.newHttpClient();

    private final ObjectMapper objectMapper = new ObjectMapper();

    {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public <T> T get(String url, Class<T> clazz) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> httpResponse;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(httpResponse.body(), clazz);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't get a value from url", e);
        }
    }
}
