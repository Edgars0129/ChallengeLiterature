package com.alurachallenge.demo.RecordDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.List;

public record DatosRespuestaAPI(
        @JsonAlias("count") Integer total,
        @JsonAlias("next") String siguiente,
        @JsonAlias("previous") String anterior,
        @JsonAlias("results") List<DatosLibro> resultados
) {}