package com.alurachallenge.demo.Model;

import com.alurachallenge.demo.RecordDTO.DatosAutor;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    // Identificador único autogenerado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre completo del autor
    @Column(nullable = false)
    private String nombre;

    // Año de nacimiento del autor (puede ser null)
    private Integer anioNacimiento;

    // Año de fallecimiento del autor (puede ser null)
    private Integer anioFallecimiento;

    // Relación muchos a muchos con libros
    @ManyToMany(mappedBy = "autores")
    private List<Libro> libros;

    // Constructor por defecto requerido por JPA
    public Autor() {}

    // Constructor para crear un autor desde los datos de la API
    public Autor(DatosAutor datos) {
        this.nombre = datos.nombre();
        this.anioNacimiento = datos.anioNacimiento();
        this.anioFallecimiento = datos.anioFallecimiento();
    }

    // Método para verificar si el autor estaba vivo en un año específico
    public boolean estabaVivoEn(int anio) {
        boolean nacidoAntes = anioNacimiento == null || anioNacimiento <= anio;
        boolean noFallecido = anioFallecimiento == null || anioFallecimiento >= anio;
        return nacidoAntes && noFallecido;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Integer getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public void setAnioFallecimiento(Integer anioFallecimiento) {
        this.anioFallecimiento = anioFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return String.format(
                "%s (%s - %s)",
                nombre,
                anioNacimiento != null ? anioNacimiento.toString() : "?",
                anioFallecimiento != null ? anioFallecimiento.toString() : "?"
        );
    }
}