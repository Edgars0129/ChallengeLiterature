package com.alurachallenge.demo.Model;

import com.alurachallenge.demo.RecordDTO.DatosLibro;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<Autor> autores = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "libro_idiomas", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "idioma")
    private Set<String> idiomas = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "libro_temas", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "tema")
    private Set<String> temas = new HashSet<>();

    private Boolean copyright;
    private Integer descargas;
    private String tipoMedia;

    // Constructor por defecto
    public Libro() {}

    // Constructor para datos de la API
    public Libro(DatosLibro datos) {
        this.id = datos.id();
        this.titulo = datos.titulo();
        this.copyright = datos.copyright();
        this.descargas = datos.descargas();
        this.tipoMedia = datos.tipoMedia();

        if (datos.idiomas() != null) {
            this.idiomas.addAll(datos.idiomas());
        }

        if (datos.temas() != null) {
            this.temas.addAll(datos.temas());
        }
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Set<Autor> getAutores() {
        return autores;
    }

    public void setAutores(Set<Autor> autores) {
        this.autores = autores;
    }

    public Set<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Set<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Set<String> getTemas() {
        return temas;
    }

    public void setTemas(Set<String> temas) {
        this.temas = temas;
    }

    public Boolean getCopyright() {
        return copyright;
    }

    public void setCopyright(Boolean copyright) {
        this.copyright = copyright;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public String getTipoMedia() {
        return tipoMedia;
    }

    public void setTipoMedia(String tipoMedia) {
        this.tipoMedia = tipoMedia;
    }

    @Override
    public String toString() {
        return String.format("""
            -----------------------------
            Título: %s
            Autor: %s
            Idioma: %s
            Número de descargas: %.1f
            -----------------------------""",
                titulo,
                !autores.isEmpty() ? autores.iterator().next().getNombre() : "Desconocido",
                String.join(", ", idiomas),
                descargas != null ? descargas.doubleValue() : 0.0
        );
    }
}