package br.com.alura.literalura.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component // Anotação que registra o cliente como um bean
public class GutendexClient {

    private static final String BASE_URL = "https://gutendex.com/books/";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public GutendexClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public Optional<JsonNode> buscarLivroPorTitulo(String titulo) {
        try {
            String tituloCodificado = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
            String url = BASE_URL + "?search=" + tituloCodificado;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JsonNode jsonResponse = objectMapper.readTree(response.body());
                return Optional.of(jsonResponse);
            } else {
                System.out.println("Erro: " + response.statusCode());
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar livro: " + e.getMessage());
        }
        return Optional.empty();
    }
}
