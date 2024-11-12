package com.alurachallenge.demo.RecordDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.List;
import java.util.Map;

public record DatosLibro(
        @JsonAlias("id") Long id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autores,
        @JsonAlias("translators") List<DatosAutor> traductores,
        @JsonAlias("subjects") List<String> temas,
        @JsonAlias("bookshelves") List<String> estantes,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("copyright") Boolean copyright,
        @JsonAlias("media_type") String tipoMedia,
        @JsonAlias("formats") Map<String, String> formatos,
        @JsonAlias("download_count") Integer descargas
) {}