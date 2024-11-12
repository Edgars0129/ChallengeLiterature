package com.alurachallenge.demo.Service;

import com.alurachallenge.demo.Exception.APIException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumoAPI {
    private final RestTemplate restTemplate;

    @Value("${api.gutendex.baseurl}")
    private String baseUrl;

    public ConsumoAPI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String obtenerDatos(String url) {
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            throw new APIException("Error al consumir la API: " + e.getMessage());
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}