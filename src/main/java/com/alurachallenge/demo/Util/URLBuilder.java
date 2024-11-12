package com.alurachallenge.demo.Util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class URLBuilder {

    @Value("${api.gutendex.baseurl}")
    private String baseUrl;

    public String buildSearchUrl(String query) {
        return UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/books")
                .queryParam("search", query)
                .build()
                .toUriString();
    }

    public String buildLanguageUrl(String language) {
        return UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/books")
                .queryParam("languages", language)
                .build()
                .toUriString();
    }

    public String buildAuthorYearUrl(Integer yearStart, Integer yearEnd) {
        return UriComponentsBuilder
                .fromUriString(baseUrl)
                .path("/books")
                .queryParam("author_year_start", yearStart)
                .queryParam("author_year_end", yearEnd)
                .build()
                .toUriString();
    }

    public String buildBaseUrl() {
        return baseUrl + "/books";
    }
}