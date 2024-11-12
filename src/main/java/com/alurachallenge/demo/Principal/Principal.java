// Principal.java
package com.alurachallenge.demo.Principal;

import com.alurachallenge.demo.Service.AutorService;
import com.alurachallenge.demo.Service.LibroService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal {
    private final Scanner teclado = new Scanner(System.in);
    private final LibroService libroService;
    private final AutorService autorService;

    public Principal(LibroService libroService, AutorService autorService) {
        this.libroService = libroService;
        this.autorService = autorService;
    }

    public void mostrarMenu() {
        var opcion = -1;

        while (opcion != 6) {
            var menu = """
                    
                    Elija la opción a través de su número:
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    6 - Salir
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1 -> buscarLibroPorTitulo();
                case 2 -> listarLibrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresPorAnio();
                case 5 -> listarLibrosPorIdioma();
                case 6 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción inválida, por favor intente nuevamente");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("\nIngrese el título del libro a buscar:");
        var titulo = teclado.nextLine();
        try {
            var libros = libroService.buscarPorTitulo(titulo);
            if (libros.isEmpty()) {
                System.out.println("\nNo se encontraron libros con ese título");
            } else {
                System.out.println("\nLibros encontrados:");
                libros.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el libro: " + e.getMessage());
        }
    }

    private void listarLibrosRegistrados() {
        try {
            var libros = libroService.listarTodos();
            if (libros.isEmpty()) {
                System.out.println("\nNo hay libros registrados en la base de datos");
            } else {
                System.out.println("\nLibros registrados:");
                libros.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error al listar los libros: " + e.getMessage());
        }
    }

    private void listarAutoresRegistrados() {
        try {
            var autores = autorService.listarTodos();
            if (autores.isEmpty()) {
                System.out.println("\nNo hay autores registrados en la base de datos");
            } else {
                System.out.println("\nAutores registrados:");
                autores.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error al listar los autores: " + e.getMessage());
        }
    }

    private void listarAutoresPorAnio() {
        System.out.println("\nIngrese el año para buscar autores:");
        try {
            var anio = Integer.parseInt(teclado.nextLine());
            var autores = autorService.buscarPorAnioVida(anio);
            if (autores.isEmpty()) {
                System.out.println("\nNo se encontraron autores vivos en ese año");
            } else {
                System.out.println("\nAutores encontrados:");
                autores.forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor ingrese un año válido");
        } catch (Exception e) {
            System.out.println("Error al buscar autores: " + e.getMessage());
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("\nIngrese el código del idioma (ejemplo: en, es, fr):");
        var idioma = teclado.nextLine().trim().toLowerCase();
        try {
            var libros = libroService.buscarPorIdioma(idioma);
            if (libros.isEmpty()) {
                System.out.println("\nNo se encontraron libros en ese idioma en la base de datos");
            } else {
                System.out.println("\nLibros encontrados:");
                libros.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error al buscar libros: " + e.getMessage());
        }
    }
}