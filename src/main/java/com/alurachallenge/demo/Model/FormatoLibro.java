package com.alurachallenge.demo.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "formatos_libro")
public class FormatoLibro {
    // Identificador único autogenerado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación muchos a uno con Libro
    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false)
    private Libro libro;

    // Tipo MIME del formato (ej: "text/html", "application/pdf")
    @Column(nullable = false)
    private String tipoMime;

    // URL donde se puede descargar el formato
    @Column(nullable = false)
    private String url;

    // Constructor por defecto requerido por JPA
    public FormatoLibro() {}

    // Constructor para crear un formato desde los datos de la API
    public FormatoLibro(String tipoMime, String url) {
        this.tipoMime = tipoMime;
        this.url = url;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public String getTipoMime() {
        return tipoMime;
    }

    public void setTipoMime(String tipoMime) {
        this.tipoMime = tipoMime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", tipoMime, url);
    }
}