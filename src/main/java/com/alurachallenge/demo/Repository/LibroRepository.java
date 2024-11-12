package com.alurachallenge.demo.Repository;

import com.alurachallenge.demo.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Buscar por título
    @Query("SELECT DISTINCT l FROM Libro l " +
            "LEFT JOIN FETCH l.autores " +
            "LEFT JOIN FETCH l.idiomas " +
            "LEFT JOIN FETCH l.temas " +
            "WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    List<Libro> findByTituloContainingIgnoreCase(@Param("titulo") String titulo);

    // Buscar por idioma
    @Query("SELECT DISTINCT l FROM Libro l " +
            "LEFT JOIN FETCH l.autores " +
            "LEFT JOIN FETCH l.idiomas " +
            "LEFT JOIN FETCH l.temas " +
            "WHERE :idioma MEMBER OF l.idiomas")
    List<Libro> findByIdioma(@Param("idioma") String idioma);

    // Sobrescribir findAll
    @Override
    @Query("SELECT DISTINCT l FROM Libro l " +
            "LEFT JOIN FETCH l.autores " +
            "LEFT JOIN FETCH l.idiomas " +
            "LEFT JOIN FETCH l.temas")
    List<Libro> findAll();

    // Verificar si existe por ID
    boolean existsById(Long id);

    // Buscar por autor
    @Query("SELECT DISTINCT l FROM Libro l " +
            "LEFT JOIN FETCH l.autores a " +
            "LEFT JOIN FETCH l.idiomas " +
            "LEFT JOIN FETCH l.temas " +
            "WHERE LOWER(a.nombre) LIKE LOWER(CONCAT('%', :nombreAutor, '%'))")
    List<Libro> findByAutorNombreContainingIgnoreCase(@Param("nombreAutor") String nombreAutor);
}