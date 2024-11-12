package com.alurachallenge.demo.Repository;

import com.alurachallenge.demo.Model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Buscar autor por nombre
    List<Autor> findByNombreContainingIgnoreCase(String nombre);

    // Buscar autores vivos en un año específico
    @Query("SELECT a FROM Autor a WHERE " +
            "(a.anioNacimiento IS NULL OR a.anioNacimiento <= :anio) AND " +
            "(a.anioFallecimiento IS NULL OR a.anioFallecimiento >= :anio)")
    List<Autor> findAutoresVivosPorAnio(@Param("anio") Integer anio);

    // Buscar autores por rango de años
    @Query("SELECT a FROM Autor a WHERE " +
            "a.anioNacimiento BETWEEN :inicio AND :fin")
    List<Autor> findByRangoAnios(@Param("inicio") Integer inicio,
                                 @Param("fin") Integer fin);
}