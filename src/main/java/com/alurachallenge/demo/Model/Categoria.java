package com.alurachallenge.demo.Model;

public enum Categoria {
    FICTION("Fiction"),
    DRAMA("Drama"),
    HISTORY("History"),
    PHILOSOPHY("Philosophy"),
    SCIENCE("Science"),
    ADVENTURE("Adventure"),
    ROMANCE("Romance"),
    HORROR("Horror"),
    POETRY("Poetry"),
    BIOGRAPHY("Biography");

    private final String nombre;

    Categoria(String nombre) {
        this.nombre = nombre;
    }

    // Obtener categoría desde string
    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.nombre.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Categoría no válida: " + text);
    }

    public String getNombre() {
        return nombre;
    }
}